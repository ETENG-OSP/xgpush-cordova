#import "CDVRegisterNotification.h"

@implementation CDVRegisterNotification

+ (void) registerNotification
{
  NSLog(@"[CDVRegisterNotification] register notification");
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= _IPHONE80_
  // iOS8注册push方法
  float sysVer = [[[UIDevice currentDevice] systemVersion] floatValue];
  if (sysVer < 8) {
    [CDVRegisterNotification registerPush];
  } else {
    [CDVRegisterNotification registerPushForIOS8];
  }
#else
  // iOS8之前注册push方法
  // 注册Push服务，注册后才能收到推送
  [CDVRegisterNotification registerPush];
#endif
}

+ (void) registerPush
{
  NSLog(@"[CDVRegisterNotification] register under ios 8");
  [[UIApplication sharedApplication] registerForRemoteNotificationTypes:(UIRemoteNotificationTypeAlert | UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound)];
}

+ (void) registerPushForIOS8
{
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= _IPHONE80_
  NSLog(@"[CDVRegisterNotification] register ios 8");

  //Types
  UIUserNotificationType types = UIUserNotificationTypeBadge | UIUserNotificationTypeSound | UIUserNotificationTypeAlert;

  //Actions
  UIMutableUserNotificationAction* acceptAction = [[UIMutableUserNotificationAction alloc] init];

  acceptAction.identifier = @"ACCEPT_IDENTIFIER";
  acceptAction.title = @"Accept";

  acceptAction.activationMode = UIUserNotificationActivationModeForeground;
  acceptAction.destructive = NO;
  acceptAction.authenticationRequired = NO;

  //Categories
  UIMutableUserNotificationCategory* inviteCategory = [[UIMutableUserNotificationCategory alloc] init];

  inviteCategory.identifier = @"INVITE_CATEGORY";

  [inviteCategory setActions:@[acceptAction] forContext:UIUserNotificationActionContextDefault];

  [inviteCategory setActions:@[acceptAction] forContext:UIUserNotificationActionContextMinimal];

  // using arc
  // [acceptAction release];

  NSSet* categories = [NSSet setWithObjects:inviteCategory, nil];

  // using arc
  // [inviteCategory release];

  UIUserNotificationSettings* mySettings = [UIUserNotificationSettings settingsForTypes:types categories:categories];

  [[UIApplication sharedApplication] registerUserNotificationSettings:mySettings];

  [[UIApplication sharedApplication] registerForRemoteNotifications];
#endif
}

@end
