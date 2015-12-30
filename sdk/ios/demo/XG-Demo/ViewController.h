//
//  ViewController.h
//  XG-Demo
//
//  Created by xiangchen on 13-11-6.
//
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController
{
    IBOutlet UIButton *btnSetTag;
    IBOutlet UIButton *btnDelTag;
    
    IBOutlet UIButton *btnLogoutDevice;
	IBOutlet UISwitch *swShowLog;
}

- (IBAction)setTag:(id)sender;
- (IBAction)delTag:(id)sender;


- (IBAction)logoutDevice:(id)sender;
@end
