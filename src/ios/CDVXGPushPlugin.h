#import <Cordova/CDV.h>
#import <Foundation/Foundation.h>

@interface CDVXGPushPlugin: CDVPlugin

- (void) didRegisterForRemoteNotificationsWithDeviceToken:deviceToken;

@end
