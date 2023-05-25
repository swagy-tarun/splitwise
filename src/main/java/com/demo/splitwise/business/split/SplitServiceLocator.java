package com.demo.splitwise.business.split;

import com.demo.splitwise.business.vo.UserShare;
import com.demo.splitwise.common.enums.ShareMethod;
import com.demo.splitwise.infrastructure.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SplitServiceLocator {

    @Autowired
    private EqualSplitStrategy equalSplitStrategy;
    @Autowired
    private PercentSplitStrategy percentSplitStrategy;

    public SplitStrategy<Map<String, Transaction>, List<UserShare>> getStrategy(final ShareMethod shareMethod) {

        if (shareMethod.equals(ShareMethod.EQUAL)) {
            return equalSplitStrategy;
        } else if (shareMethod.equals(ShareMethod.PERCENTAGE)) {
            return percentSplitStrategy;
        }

        return equalSplitStrategy;
    }

}
