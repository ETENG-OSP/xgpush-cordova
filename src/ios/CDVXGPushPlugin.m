#import "CDVXGPushPlugin.h"
#import "XGPush.h"

@implementation CDVXGPushPlugin

- (void) didRegisterForRemoteNotificationsWithDeviceToken:(NSData*)deviceToken
{
  void (^successBlock)(void) = ^(void) {
    NSLog(@"[XGPush] register successBlock");
  };

  void (^errorBlock)(void) = ^(void) {
    NSLog(@"[XGPush] register errorBlock");
  };

  [XGPush registerDevice:deviceToken successCallback:successBlock errorCallback:errorBlock];
}

@end
