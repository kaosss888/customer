package com.application.passbook.customer.controller;

import com.application.passbook.customer.log.LogConstants;
import com.application.passbook.customer.log.LogGenerator;
import com.application.passbook.customer.service.IFeedbackService;
import com.application.passbook.customer.service.IGainPassTemplateService;
import com.application.passbook.customer.service.IInventoryService;
import com.application.passbook.customer.service.IUserPassService;
import com.application.passbook.customer.vo.Feedback;
import com.application.passbook.customer.vo.GainPassTemplateRequest;
import com.application.passbook.customer.vo.Pass;
import com.application.passbook.customer.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>Passbook Rest Controller</h1>
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class PassbookController {

    @Autowired
    private IUserPassService userPassService;

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private IGainPassTemplateService gainPassTemplateService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * <h2>获取用户个人的优惠券信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/userPassInfo")
    public Response userPassInfo(@RequestParam("userId") Long userId) throws Exception {
        LogGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.USER_PASS_INFO,
                null
        );
        return userPassService.getUserPassInfo(userId);
    }

    /**
     * <h2>获取用户使用了的优惠券信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/userUsedPassInfo")
    public Response userUsedPassInfo(@RequestParam("userId") Long userId) throws Exception {
        LogGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.USER_USED_PASS_INFO,
                null
        );
        return userPassService.getUserUsedPassInfo(userId);
    }

    /**
     * <h2>用户使用优惠券</h2>
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/userUsePass")
    public Response userUsePass(@RequestBody Pass pass) {

        LogGenerator.genLog(
                httpServletRequest,
                pass.getUserId(),
                LogConstants.ActionName.USER_USE_PASS,
                null
        );
        return userPassService.userUsePass(pass);
    }

    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/inventoryInfo")
    public Response inventoryInfo(@RequestParam("userId") Long userId) throws Exception {

        LogGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.INVENTORY_INFO,
                null
        );
        return inventoryService.getInventoryInfo(userId);
    }

    /**
     * <h2>用户领取优惠券</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/gainPassTemplate")
    public Response gainPassTemplate(@RequestBody GainPassTemplateRequest request) throws Exception {

        LogGenerator.genLog(
                httpServletRequest,
                request.getUserId(),
                LogConstants.ActionName.GAIN_PASS_TEMPLATE,
                request
        );

        return gainPassTemplateService.gainPassTemplate(request);
    }

    /**
     * <h2>用户创建评论</h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    @ResponseBody
    @PostMapping("/createFeedback")
    public Response createFeedback(@RequestBody Feedback feedback) {

        LogGenerator.genLog(
                httpServletRequest,
                feedback.getUserId(),
                LogConstants.ActionName.CREATE_FEEDBACK,
                feedback
        );

        return feedbackService.createFeedback(feedback);
    }

    /**
     * <h2>用户获取评论信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    @ResponseBody
    @GetMapping("/getFeedback")
    public Response getFeedback(Long userId) {

        LogGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.CREATE_FEEDBACK,
                null
        );

        return feedbackService.getFeedback(userId);
    }

    /**
     * <h2>异常演示</h2>
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/exception")
    public Response exception() throws Exception {
        throw new Exception("exception advice test");
    }
}
