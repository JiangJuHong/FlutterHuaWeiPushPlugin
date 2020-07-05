package top.huic.hua_wei_push_plugin;


import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

/**
 * 自定义华为消息服务
 *
 * @author 蒋具宏
 */
public class CustomHmsMessageService extends HmsMessageService {
    /**
     * 接收透传消息方法。
     *
     * @param remoteMessage 消息数据
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    /**
     * 发送上行消息成功回调方法。
     *
     * @param s 消息ID。
     */
    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    /**
     * 发送上行消息时如果使用了消息回执能力，消息到达App服务器后，App服务器的应答消息通过本方法回调给应用。
     *
     * @param s 消息ID
     * @param e BaseException类型，回执消息中携带的异常信息。
     */
    @Override
    public void onMessageDelivered(String s, Exception e) {
        super.onMessageDelivered(s, e);
    }

    /**
     * 发送上行消息失败回调方法。
     *
     * @param s 消息ID。
     * @param e BaseException类型，通过getErrorCode()获取返回的错误码，通过getMessage()获取错误描述信息
     */
    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }

    /**
     * 服务端更新token回调方法。
     *
     * @param s Push SDK返回的Token。
     */
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    /**
     * 申请token失败回调方法。
     *
     * @param e BaseException类型，App调用getToken方法申请Token失败时返回的异常。
     */
    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);
    }
}
