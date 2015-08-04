//
//  CDVRegisterPush.h
//  HelloCordova
//
//  Created by Gen Liu on 8/4/15.
//
//

#import <Foundation/Foundation.h>

@interface CDVRegisterNotification : NSObject

+ (void) registerPush;
+ (void) registerPushForIOS8;
+ (void) registerNotification;

@end
