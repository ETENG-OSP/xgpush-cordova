#import "AppDelegate+CDVXGPush.h"
#import "CDVRegisterNotification.h"
#import "CDVXGPushUtil.h"
#import <objc/runtime.h>

@implementation AppDelegate (CDVXGPush)

+ (void) load {
  Method original, swizzled;
  original = class_getInstanceMethod(self, @selector(application:didFinishLaunchingWithOptions:));
  swizzled = class_getInstanceMethod(self, @selector(swizzled_application:didFinishLaunchingWithOptions:));
  method_exchangeImplementations(original, swizzled);
}

/**
 * app start up
 */
- (BOOL) swizzled_application:(UIApplication*)application didFinishLaunchingWithOptions:(NSDictionary*)launchOptions
{
  NSLog(@"> prepare start xgpush");

  // 注册通知
  // [CDVRegisterNotification registerNotification];

  // 启动 XGPush
  [CDVXGPushUtil startApp];
  [CDVXGPushUtil initForReregister];
  [CDVXGPushUtil handleLaunching:launchOptions];

  return [self swizzled_application:application didFinishLaunchingWithOptions:launchOptions];
}

/**
 * feedback
 */
- (void) application:(UIApplication*)application didReceiveRemoteNotification:(NSDictionary*)userInfo
{
  NSLog(@"[XGPush] xgpush receive remote notification");
  //推送反馈(app运行时)
  //[XGPush handleReceiveNotification:userInfo];
}

- (void) application:(UIApplication*)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData*)deviceToken
{
  NSLog(@"[XGPush] receive device token: %@", deviceToken);
  [CDVXGPushUtil registerDevice:deviceToken];
}

- (void) application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
  NSLog(@"[XGPush] register error: %@", error);
}

- (id) getCommandInstance:(NSString*)className
{
  return [self.viewController getCommandInstance:className];
}

@end
