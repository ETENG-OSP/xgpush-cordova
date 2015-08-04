#import "CDVXGPushUtil.h"
#import "CDVRegisterNotification.h"
#import "XGPush.h"

@implementation CDVXGPushUtil

+ (void) handleLaunching:(NSDictionary*)launchOptions
{
  NSLog(@"[XGPush] handle launching");
  // 推送反馈回调版本示例
  void (^successBlock)(void) = ^(void)
  {
    // 成功之后的处理
    NSLog(@"[XGPush]handleLaunching's successBlock");
  };

  void (^errorBlock)(void) = ^(void)
  {
    // 失败之后的处理
    NSLog(@"[XGPush]handleLaunching's errorBlock");
  };

  [XGPush handleLaunching:launchOptions successCallback:successBlock errorCallback:errorBlock];
}

+ (void) initForReregister
{
  NSLog(@"[XGPush] init for re-register");
  void (^successCallback)(void) = ^(void)
  {
    // 如果变成需要注册状态
    if (![XGPush isUnRegisterStatus])
    {
      [CDVRegisterNotification registerNotification];
    }
  };

  [XGPush initForReregister:successCallback];
}

+ (void) startApp
{
  uint32_t accessID = [CDVXGPushUtil getAccessID];
  NSString* accessKey = [CDVXGPushUtil getAccessKey];
  NSLog(@"[XGPush] starting with access id: %u, access key: %@", accessID, accessKey);
  [XGPush startApp:accessID appKey:accessKey];
}

+ (void) registerDevice:(NSData*)deviceToken {
  [XGPush registerDevice:deviceToken];
}

/**
 * get xgpush access id
 */
+ (uint32_t) getAccessID
{
  return [[[[NSBundle mainBundle] objectForInfoDictionaryKey:@"XGPushMeta"] valueForKey:@"AccessID"] intValue];
}

/**
 * get xgpush access key
 */
+ (NSString*) getAccessKey
{
  return [[[NSBundle mainBundle] objectForInfoDictionaryKey:@"XGPushMeta"] valueForKey:@"AccessKey"];
}

@end
