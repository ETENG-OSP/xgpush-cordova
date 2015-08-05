#import "CDVXGPushUtil.h"
#import <Cordova/CDV.h>
#import <Foundation/Foundation.h>

@interface CDVXGPushPlugin: CDVPlugin

- (void) didRegisterForRemoteNotificationsWithDeviceToken:(NSNotification*)notification;
- (void) didFailToRegisterForRemoteNotificationsWithError:(NSNotification*)notification;
- (void) didReceiveRemoteNotification:(NSNotification*)notification;

@property CDVXGPushUtil* util;
@property NSMutableArray* callbackIds;

@end
