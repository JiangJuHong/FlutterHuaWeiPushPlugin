package top.huic.hua_wei_push_plugin

import android.content.Context
import androidx.annotation.NonNull
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hmf.tasks.Task
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.aaid.entity.AAIDResult
import com.huawei.hms.common.ApiException
import com.huawei.hms.push.HmsMessaging
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** HuaWeiPushPlugin */
public class HuaWeiPushPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private lateinit var hmsInstance: HmsInstanceId

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        hmsInstance = HmsInstanceId.getInstance(context)
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "hua_wei_push_plugin")
        channel.setMethodCallHandler(this);
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getToken" -> {
                this.getToken(call, result)
            }
            "deleteToken" -> {
                this.deleteToken(call, result)
            }
            "getId" -> {
                this.getId(call, result)
            }
            "getAAID" -> {
                this.getAAID(call, result)
            }
            "deleteAAID" -> {
                this.deleteAAID(call, result)
            }
            "getAppId" -> {
                this.getAppId(call, result)
            }
            "getCreationTime" -> {
                this.getCreationTime(call, result)
            }
            "getValue" -> {
                this.getValue(call, result)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    /**
     * 获得 Push Token
     */
    private fun getToken(@NonNull call: MethodCall, @NonNull result: Result) {
        val appId: String? = call.argument("appId")
        object : Thread() {
            override fun run() {
                try {
                    CommonUtil.runMainThreadReturn(result, hmsInstance.getToken(appId, HmsMessaging.DEFAULT_TOKEN_SCOPE))
                } catch (e: ApiException) {
                    CommonUtil.runMainThreadReturnError(result, e.statusCode.toString(), e.message, e.message)
                }
            }
        }.start()
    }

    /**
     * 删除 Push Token
     */
    private fun deleteToken(@NonNull call: MethodCall, @NonNull result: Result) {
        val appId: String? = call.argument("appId")
        object : Thread() {
            override fun run() {
                hmsInstance.deleteToken(appId, HmsMessaging.DEFAULT_TOKEN_SCOPE)
                CommonUtil.runMainThreadReturn(result, null);
            }
        }.start()
    }

    /**
     * 获得 id
     */
    private fun getId(@NonNull call: MethodCall, @NonNull result: Result) {
        result.success(hmsInstance.id)
    }

    /**
     * 获得 aaid
     */
    private fun getAAID(@NonNull call: MethodCall, @NonNull result: Result) {
        val aaidResultTask: Task<AAIDResult> = HmsInstanceId.getInstance(context).aaid
        aaidResultTask.addOnSuccessListener { aaidResult ->
            result.success(aaidResult.id)
        }.addOnFailureListener { e -> result.error("-1", e.message, e.message) }
    }

    /**
     * 删除 aaid
     */
    private fun deleteAAID(@NonNull call: MethodCall, @NonNull result: Result) {
        try {
            hmsInstance.deleteAAID();
            result.success(null);
        } catch (e: ApiException) {
            result.error(e.statusCode.toString(), e.message, e.message)
        }
    }

    /**
     * 获得 appid
     */
    private fun getAppId(@NonNull call: MethodCall, @NonNull result: Result) {
        result.success(AGConnectServicesConfig.fromContext(context).getString("client/app_id"))
    }

    /**
     * 获得 CreationTime
     */
    private fun getCreationTime(@NonNull call: MethodCall, @NonNull result: Result) {
        result.success(hmsInstance.creationTime)
    }

    /**
     * 获得值，key为 agconnect-services.json 的Key，以斜杠区分
     */
    private fun getValue(@NonNull call: MethodCall, @NonNull result: Result) {
        val key: String = CommonUtil.getParam(call, result, "key")
        result.success(AGConnectServicesConfig.fromContext(context).getString(key))
    }
}