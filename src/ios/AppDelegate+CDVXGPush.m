#import "AppDelegate+CDVXGPush.h"

@implementation AppDelegate (CDVXGPush)

/**
 * 追加推送事件
 */
- (void) application:(UIApplication*)application didReceiveRemoteNotification:(NSDictionary*)userInfo {
  NSLog(@"[AppDelegate] receive remote notification");
  [[NSNotificationCenter defaultCenter] postNotificationName: @"receivenotification" object:userInfo];
}

@end
