package aystzh.com.base.exception.assertion;



import aystzh.com.base.exception.BaseException;
import aystzh.com.base.exception.BusinessException;
import aystzh.com.base.exception.constant.IResponseEnum;

import java.text.MessageFormat;

/**
 * <p>业务异常断言</p>
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BusinessException(this, args, msg, t);
    }

}
