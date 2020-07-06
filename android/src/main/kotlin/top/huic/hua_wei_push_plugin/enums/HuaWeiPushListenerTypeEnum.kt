package top.huic.hua_wei_push_plugin.enums

/**
 * 监听器枚举类型
 * @author 蒋具宏
 */
enum class HuaWeiPushListenerTypeEnum {
    MessageReceived,
    MessageSent,
    MessageDelivered,
    SendError,
    NewToken,
    TokenError
}