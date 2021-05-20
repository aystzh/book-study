package leecode.tree;

/**
 * 递归模板
 * Created by zhanghuan on 2020/7/3.
 */
public class RecurTemplate {


    private static final int MAX_LEVEL = 12;

    /**
     * 1、抵制人肉递归
     * 2、找最近重复性
     * 3、数学归纳思维
     *
     * @param level
     * @param param
     */
    public void recurTemplateTest(int level, int param) {
        //terminator 递归终结条件
        if (level > MAX_LEVEL) {
            //process result
            return;
        }
        //process current logic 处理当前层逻辑
        //  process(level, param);

        //drill down 下探到下一层
        //   recurTemplateTest(level + 1, newParams);
        // restore current status //清扫当前层
    }
}
