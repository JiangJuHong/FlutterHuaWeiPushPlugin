package top.huic.hua_wei_push_plugin

import android.content.Context
import androidx.annotation.NonNull;
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.push.HmsMessaging

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

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

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "hua_wei_push_plugin")
            channel.setMethodCallHandler(HuaWeiPushPlugin())
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    /**
     * 获得 Push Token
     */
    private fun getToken(@NonNull call: MethodCall, @NonNull result: Result) {
        val appId = ""
        result.success(hmsInstance.getToken(appId, HmsMessaging.DEFAULT_TOKEN_SCOPE))
    }

    /**
     * 删除 Push Token
     */
    private fun deleteToken(@NonNull call: MethodCall, @NonNull result: Result) {
        val appId = ""
        hmsInstance.deleteToken(appId, HmsMessaging.DEFAULT_TOKEN_SCOPE)
        result.success(null)
    }
}