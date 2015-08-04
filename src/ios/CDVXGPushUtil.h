#import <Foundation/Foundation.h>

@interface CDVXGPushUtil: NSObject

+ (void) startApp;
+ (void) handleLaunching:(NSDictionary*)launchOptions;
+ (void) initForReregister;
+ (void) registerDevice:(NSData*)deviceToken;
+ (uint32_t) getAccessID;
+ (NSString*) getAccessKey;

@end
