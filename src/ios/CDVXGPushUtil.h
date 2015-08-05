#import <Foundation/Foundation.h>

@interface CDVXGPushUtil: NSObject

- (void) startApp;
- (void) initForReregister;
- (void) registerDevice:(NSData*)deviceToken;
- (void) registerPush:(NSString*)alias successCallback:(void (^)(void))success errorCallback:(void (^)(void))error;
- (uint32_t) getAccessID;
- (NSString*) getAccessKey;

@property NSData* deviceToken;

@end
