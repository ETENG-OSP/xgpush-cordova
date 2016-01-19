//
//  XGSetting.h
//  XG-SDK
//
//  Created by xiangchen on 29/08/14.
//  Copyright (c) 2014 mta. All rights reserved.
//

#import <Foundation/Foundation.h>

#define XG_SDK_VERSION @"2.4.6"

@interface XGSetting : NSObject

@property (nonatomic,retain) NSString* Channel;
@property (nonatomic,retain) NSString* GameServer;

+(id)getInstance;

-(void) enableDebug:(BOOL) enbaleDebug;
-(BOOL) isEnableDebug;

@end
