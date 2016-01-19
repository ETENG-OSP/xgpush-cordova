//
//  ViewController.m
//  XG-Demo
//
//  Created by xiangchen on 13-11-6.
//
//

#import "ViewController.h"
#import "XGPush.h"
#import "XGSetting.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	
	XGSetting *setting = [XGSetting getInstance];
	[setting enableDebug:self->swShowLog.on];
	
    //btnLogoutDevice.enabled = false;
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)setTag:(id)sender{
    //为不同的"用户"设置标签
    
    NSString * tag = @"name:sean";
    
    void (^successBlock)(void) = ^(void){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
                                                        message:@"设置标签成功"
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
		[alert release];
    };
    
    void (^errorBlock)(void) = ^(void){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
                                                        message:@"设置标签失败"
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
		[alert release];
    };
    
    [XGPush setTag:tag successCallback:successBlock errorCallback:errorBlock];
	
    //[XGPush setTag:tag];
    
}

- (IBAction)delTag:(id)sender{
    NSString * tag = @"age:10";
    
    
    void (^successBlock)(void) = ^(void){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
                                                        message:@"删除标签成功"
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
		[alert release];
    };
    
    void (^errorBlock)(void) = ^(void){
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
                                                        message:@"删除标签失败"
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
		[alert release];
    };

    [XGPush delTag:tag successCallback:successBlock errorCallback:errorBlock];
    //[XGPush delTag:tag];
}

- (IBAction)logoutDevice:(id)sender{
	[XGPush unRegisterDevice:^{
		    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
		                                                    message:@"注销设备success"
		                                                   delegate:nil
		                                          cancelButtonTitle:@"OK"
		                                          otherButtonTitles:nil];
		    [alert show];
			[alert release];

	} errorCallback:^{
		
		    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"信鸽推送"
		                                                    message:@"注销设备fail"
		                                                   delegate:nil
		                                          cancelButtonTitle:@"OK"
		                                          otherButtonTitles:nil];
		    [alert show];
			[alert release];

	}];
}

- (IBAction)toggleDebug:(id)sender
{
	XGSetting *setting = [XGSetting getInstance];
	[setting enableDebug:self->swShowLog.on];
}

@end
