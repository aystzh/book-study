package aystzh.com.base.exception.assertion;




import aystzh.com.base.exception.ArgumentException;
import aystzh.com.base.exception.BaseException;
import aystzh.com.base.exception.constant.IResponseEnum;

import java.text.MessageFormat;


public interface CommonExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new ArgumentException(this, args, msg, t);
    }

}
