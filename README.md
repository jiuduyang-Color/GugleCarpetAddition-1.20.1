# Gugle 的 Carpet 附加包（GCA）— 1.20.1 Forge 移植版

[English](#english)

GCA（Gugle 的 Carpet 附加包）是 [Carpet](https://github.com/gnembon/fabric-carpet) 的 **Forge 移植版**，移植到了 **1.20.1**。它是一款服务端扩展模组，为原版生存服务器提供一整套实用工具，所有功能都通过 `/carpet` 指令开关，分类标签为 `GCA`。

原项目基于 Fabric 开发并提供多版本构建，但 Forge 端只发布到 1.19.2 为止。本仓库是基于原项目 1.20.1 分支源码移植的 Minecraft 1.20.1 Forge 版本，功能与原版保持一致。

## 一、假人（Bot）管理

Carpet 假人的全方位增强，让假人更接近"真正的玩家"：

- **打开假人背包 / 末影箱**：右键（或潜行+右键）假人即可查看、操作其背包与末影箱
- **假人驻留**：退出存档时不删除假人
- **假人动作保存**：重载存档时保留假人的动作
- **自动重生**：假人死亡后可在重生点 / 死亡点 / 设定点自动重生
- **自动补货**：包括支持从潜影盒自动补货
- **自动钓鱼**：鱼钩上钩后自动收竿、重新抛竿
- **自动换工具**：工具损坏后自动切换备用工具（可保留经验修补工具的耐久）
- **工具损坏 / 补货失败广播**：向全服提示
- **命名**：为假人统一添加前缀 / 后缀，或用离线 UUID 登录

配合 `/bot` 指令，还提供：

- **假人管理菜单**：保存 / 加载 / 分组 / 批量生成假人
- **假人控制器**：自定义额外控制面板按钮，一键执行动作
- **假人动作管理**：增删动作、设置登入后自动执行

## 二、指令系统

- `/todo` — 待办事项管理（添加 / 删除 / 标记完成）
- `/here` — 向全服广播自己的坐标（含维度换算、Xaero 小地图路点）
- `/whereis`（`/vris`）— 定位某个玩家
- `/loc` — 地标管理（保存 / 查看 / 删除，含下界坐标换算）
- `/wlist` — 白名单管理，可授权普通玩家管理
- `/blist` — 封禁名单管理
- `/sop` — 简单获取 OP
- `/seed` — 自定义种子命令权限
- `/transfer` — 服务器间转移玩家
- `/tick` — 修复 `/tick` 命令权限

## 三、交互体验优化

- **更好的栅栏门放置**：新放的栅栏门复制被点击栅栏门的朝向、开关状态
- **更好的原木去皮**：只有命名含"去皮 / Strip"的斧头才能去皮
- **更好的告示牌交互**：右键墙上告示牌相当于与背后方块交互
- **更好的展示框交互**：右键展示框与附着的方块交互，可切换可见性、取物
- **更好的快速合成**：Shift 快速合成时在背包保留一份材料

## 四、便捷工具

- **游戏内计算器**：聊天栏输入 `==表达式` 即可计算，支持 `sin`、`cos`、`sqrt`、`log`、`pi`、`e` 等
- **快速 Ping 好友**：`@ 玩家` 普通提醒、`@@ 玩家` 紧急提醒（带音效与屏幕标题）
- **欢迎玩家**：玩家进服发送自定义欢迎语
- **流浪商人提醒**：生成成功 / 失败时提醒并说明原因
- **修复末地水晶同步**
- **列表分页**：设置 GCA 各列表每页条目数

## 关于前置模组 Carpet

Fabric 版 Carpet 无法在 Forge 上运行，因此本移植版依赖 **Carpet 的 Forge 移植版**：

- 名称：Carpet: NeoForged（Forge Backport）
- 文件：`forge-carpet-1.20.1-1.0.8+v251027.jar`
- 作者：chililisoup
- 来源：`https://github.com/chililisoup/neoforge-carpet` / `https://modrinth.com/mod/neoforge-carpet`
- 许可：MIT

> 前置模组不随本仓库分发，请从上述来源自行下载。

## 许可

本项目遵循原项目的 **MIT** 许可。原始版权归 Gugle（Gu-ZT）所有，移植部分版权归 jiuduyang 所有。详见 `LICENSE` 与 `文档/许可与来源说明.md`。

---

## English

GCA (Gugle's Carpet Addition) is a **Forge port** of [Carpet](https://github.com/gnembon/fabric-carpet), ported to **1.20.1**. It is a server-side extension mod that provides a set of practical tools for vanilla survival servers. All features are toggled via the `/carpet` command under the `GCA` category.

The original project is developed on Fabric and provides multi-version builds, but Forge builds were only released up to 1.19.2. This repository is a Minecraft 1.20.1 Forge port based on the original project's 1.20.1 branch source code, with features identical to the original.

### 1. Fake Player (Bot) Management

Comprehensive enhancements for Carpet fake players, making them closer to "real players":

- **Open fake player inventory / ender chest**: right-click (or sneak + right-click) a fake player to view and manage its inventory and ender chest
- **Fake player resident**: fake players are kept when leaving the world
- **Fake player action save**: fake player actions are kept on world reload
- **Auto respawn**: fake players can auto-respawn at their spawn point / death point / a designated point
- **Auto replenishment**: including replenishing from shulker boxes
- **Auto fishing**: automatically reel in and re-cast after a catch
- **Auto tool replacement**: automatically switch to a backup tool when the current one breaks (can preserve mending tools' durability)
- **Tool damage / replenishment failure broadcast**: notify the whole server
- **Naming**: add a unified prefix/suffix to fake players, or log in with an offline UUID

With the `/bot` command:

- **Fake player management menu**: save / load / group / batch-generate fake players
- **Bot controller**: customize extra control-panel buttons to trigger actions in one click
- **Fake player action management**: add/remove actions, set actions to run automatically on login

### 2. Commands

- `/todo` — todo list management (add / remove / mark as done)
- `/here` — broadcast your location to the whole server (with dimension conversion and Xaero map waypoints)
- `/whereis` (`/vris`) — locate a player
- `/loc` — waypoint management (save / view / delete, with Nether coordinate conversion)
- `/wlist` — whitelist management, can authorize normal players to manage it
- `/blist` — ban list management
- `/sop` — quickly obtain OP
- `/seed` — customize the `/seed` command permission
- `/transfer` — transfer players between servers
- `/tick` — fix the `/tick` command permission

### 3. Interaction Improvements

- **Better fence gate placement**: newly placed fence gates copy the clicked fence gate's orientation and open state
- **Better log stripping**: only axes named with "去皮" / "Strip" can strip logs
- **Better sign interaction**: right-clicking a wall sign interacts with the block behind it
- **Better item frame interaction**: right-clicking an item frame interacts with the attached block; can toggle visibility and take items
- **Better quick crafting**: keep one copy of each material when shift quick-crafting

### 4. Utilities

- **In-game calculator**: type `==expression` in chat to calculate, supporting `sin`, `cos`, `sqrt`, `log`, `pi`, `e`, etc.
- **Quick ping friends**: `@ player` for a normal reminder, `@@ player` for an urgent reminder (with sound and on-screen title)
- **Welcome player**: send a custom welcome message when a player joins
- **Wandering trader reminder**: remind on successful/failed spawning with the reason
- **Fix end crystal synchronization**
- **List pagination**: set the number of entries per GCA list page

### About the Carpet Dependency

The Fabric version of Carpet cannot run on Forge, so this port depends on the **Carpet Forge port**:

- Name: Carpet: NeoForged (Forge Backport)
- File: `forge-carpet-1.20.1-1.0.8+v251027.jar`
- Author: chililisoup
- Source: `https://github.com/chililisoup/neoforge-carpet` / `https://modrinth.com/mod/neoforge-carpet`
- License: MIT

> The dependency mod is not distributed with this repository. Please download it from the sources above.

### License

This project follows the original project's **MIT** license. Original copyright belongs to Gugle (Gu-ZT); the port copyright belongs to jiuduyang. See `LICENSE` and `文档/许可与来源说明.md` for details.