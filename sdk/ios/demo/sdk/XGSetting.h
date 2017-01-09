//
//  XGSetting.h
//  XG-SDK
//
//  Created by xiangchen on 29/08/14.
//  Copyright (c) 2014 mta. All rights reserved.
//

#import <Foundation/Foundation.h>

#define XG_SDK_VERSION @"2.5.0"

@interface XGSetting : NSObject

@property (nonatomic,retain) NSString* Channel DEPRECATED_MSG_ATTRIBUTE("此属性已经废弃");
@property (nonatomic,retain) NSString* GameServer DEPRECATED_MSG_ATTRIBUTE("此属性已经废弃");

+(id)getInstance;

-(void) enableDebug:(BOOL) enbaleDebug;
-(BOOL) isEnableDebug;

@end
