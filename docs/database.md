# Database Documentation – GS Bot

## Overview
The gs-bot uses a MySQL database to store per-guild configuration.
This allows the bot to operate on multiple Discord servers with
individual settings and enables future extensions such as a web-based
configuration panel.

The database connection is managed via HikariCP and is validated during
bot startup using a healthcheck.

---

## Database Connection
- Provider: external hosting provider
- Type: MySQL
- Connection: JDBC
- SSL: enabled

The bot initializes the database connection **before** connecting to Discord.
If the database is not reachable, the bot will not start.

---

## Tables

### `gsb_guild_config`

This table stores the configuration for each Discord guild (server).
There is exactly **one row per guild**.

#### Columns

| Column          | Type               | Description |
|-----------------|--------------------|-------------|
| `guild_id`      | BIGINT (PK)        | Discord guild ID |
| `enabled`       | TINYINT(1)         | Enables or disables the bot for this guild |
| `locale`        | VARCHAR(10)        | Language code (e.g. `de`, `en`) |
| `config_version`| INT                | Version of the configuration schema |
| `features_json` | JSON               | Feature toggles (welcome, logger, embeds, etc.) |
| `channels_json` | JSON               | Discord channel IDs used by the bot |
| `roles_json`    | JSON               | Discord role IDs used by the bot |
| `settings_json` | JSON               | Feature-specific settings and extended configuration |
| `created_at`    | TIMESTAMP          | Creation timestamp |
| `updated_at`    | TIMESTAMP          | Last update timestamp |

---

## JSON Field Philosophy

The configuration is intentionally stored using JSON fields to allow
flexible and incremental changes without frequent database migrations.

### `features_json`
Stores boolean flags for enabling or disabling bot features.<br/>
Example:
```json
{
  "welcome": true,
  "verification": false,
  "selfroles": false,
  "logger": true,
  "embeds": true
}
```
---

### `channels_json`
Maps logical channel purposes to Discord channel IDs.<br/>
```json
{
"logs": "123456789012345678",
"welcome": null
}
```
---

### `roles_json`
Maps logical role purposes to Discord role IDs.<br/>
``` json
{
  "verified": "987654321098765432"
}
```
---

### `settings_json`
Contains advanced or feature-specific settings.<br/>
``` json
{
  "verification": {
    "method": "button",
    "timeout": 300
  },
  "levels": {
    "enabled": false,
    "xp_per_message": 5
  }
}
```
---

## Lifecycle
* On startup:
   * Database connection pool is initialized
   * A healthcheck (SELECT 1) validates connectivity<br/></br>

* On guild join (future):
   * A default configuration row will be created if none exists<br/></br>

* During runtime:
   * Configuration is loaded per guild and cached in memory
---

## Notes
* JSON structure validation is handled in the application layer
* `config_version` allows future migrations without breaking old configs
* This schema is shared across all environments (dev / production)
