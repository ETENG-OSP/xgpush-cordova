#import "CDVXGPushUtil.h"
#import "CDVRegisterNotification.h"
#import "XGPush.h"

@implementation CDVXGPushUtil

/**
 * 注册通知
 */
- (void) initForReregister {
  NSLog(@"[XGPush] init for re-register");
  [XGPush initForReregister:^{
    // 如果变成需要注册状态
    if (![XGPush isUnRegisterStatus]) {
      [CDVRegisterNotification registerNotification];
    }
  }];
}

/**
 * 启动 xgpush
 */
- (void) startApp {
  uint32_t accessID = [self getAccessID];
  NSString* accessKey = [self getAccessKey];
  NSLog(@"[XGPush] starting with access id: %u, access key: %@", accessID, accessKey);
  [XGPush startApp:accessID appKey:accessKey];
}

/**
 * 记录 device token
 *
 * @param  {NSData*} deviceToken [description]
 * @return {[type]}              [description]
 */
- (void) registerDevice:(NSData*)deviceToken {
  NSLog(@"[XGPush] register with token:%@", deviceToken);
  self.deviceToken = deviceToken;
}

/**
 * 注册 xgpush
 *
 * @param  {[type]} void [description]
 * @return {[type]}      [description]
 */
- (void) registerPush:(NSString*)alias successCallback:(CallbackBlock)success errorCallback:(CallbackBlock)error {
  NSLog(@"[XGPush] register with token:%@ alias:%@", self.deviceToken, alias);
  if ([alias respondsToSelector:@selector(length)] && [alias length] > 0) {
    NSLog(@"[XGPush] setting alias:%@", alias);
    [XGPush setAccount:alias];
  }

  [XGPush registerDevice:self.deviceToken successCallback:success errorCallback:error];
}

/**
 * 停止接收
 */
- (void)unregisterPush:(CallbackBlock)success errorCallback:(CallbackBlock)error {
  [XGPush unRegisterDevice:success errorCallback:error];
}

/**
 * 获取 access id
 *
 * @param  {[type]} uint32_t [description]
 * @return {[type]}          [description]
 */
- (uint32_t) getAccessID {
  return [[[[NSBundle mainBundle] objectForInfoDictionaryKey:@"XGPushMeta"] valueForKey:@"AccessID"] intValue];
}

/**
 * 获取 access key
 *
 * @param  {[type]} NSString* [description]
 * @return {[type]}           [description]
 */
- (NSString*) getAccessKey {
  return [[[NSBundle mainBundle] objectForInfoDictionaryKey:@"XGPushMeta"] valueForKey:@"AccessKey"];
}

@end
