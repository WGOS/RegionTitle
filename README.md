## Region Title
Region Title - is a simple plugin that allows you to set greetings title (as well as subtitle) for claimed region
and also play sound

## Supported plugins
At this moment plugin supports only [RedProtect](https://github.com/FabioZumbi12/RedProtect) but this may change in the future
(actually if I decide to change claiming plugin on my server :D)  
**Notice that only RedProtect 7.7.2 and above is supported!**

## How to use
All settings done by flags for claimed region

| Flag                 | Value type | Default value | Description                                                                         |
|----------------------|------------|---------------|-------------------------------------------------------------------------------------|
| title-main           | string     | null          | Set title to show to player. Set to false or empty string to disable title at all   |
| title-subtitle       | string     | null          | Set subtitle for title Set to false or empty string to diable subtitle              |
| title-fade-in-ticks  | integer    | 10            | Number of ticks for title to fade in                                                |
| title-fade-out-ticks | integer    | 10            | Number of ticks for title to fade out                                               |
| title-stay-ticks     | integer    | 40            | How many ticks title will stay on screen before fade out                            |
| enter-sound          | string     | null          | Play sound when player enters region. Set to flase or empty string to disable sound |