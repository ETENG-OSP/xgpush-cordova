#import <Foundation/Foundation.h>

@interface CDVRegisterNotification : NSObject

+ (void) registerPush;
+ (void) registerPushForIOS8;
+ (void) registerNotification;

@end
