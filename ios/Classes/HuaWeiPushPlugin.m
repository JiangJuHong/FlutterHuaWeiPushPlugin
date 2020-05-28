#import "HuaWeiPushPlugin.h"
#if __has_include(<hua_wei_push_plugin/hua_wei_push_plugin-Swift.h>)
#import <hua_wei_push_plugin/hua_wei_push_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "hua_wei_push_plugin-Swift.h"
#endif

@implementation HuaWeiPushPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftHuaWeiPushPlugin registerWithRegistrar:registrar];
}
@end
