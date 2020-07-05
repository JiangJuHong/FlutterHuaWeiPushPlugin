package top.huic.hua_wei_push_plugin;

import android.content.Intent;
import android.os.IBinder;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

/**
 * 自定义华为消息服务
 *
 * @author 蒋具宏
 */
public class CustomHmsMessageService extends HmsMessageService {
    public CustomHmsMessageService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onMessageDelivered(String s, Exception e) {
        super.onMessageDelivered(s, e);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i1) {
        return super.onStartCommand(intent, i, i1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
