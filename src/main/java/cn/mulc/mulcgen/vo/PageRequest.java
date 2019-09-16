package cn.mulc.mulcgen.vo;

import lombok.Data;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.vo
 * @ClassName: PageRequest
 * @Author: llnlg
 * @Description: 分页封装实体类
 * @Date: 2019/9/16 15:53
 * @Version: 1.0
 */
@Data
public class PageRequest {
    //页码
    int pageNum;
    //页面大小
    int pageSize;
}
