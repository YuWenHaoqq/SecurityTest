package priv.wenhao.securitytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;

/**
 * ClassName: SchoolTeacherMapper
 * Description: 关于教师表的表操作
 * Author: yuWenHao
 * Date: 2020/4/23
 */

@Mapper
public interface SchoolTeacherMapper extends BaseMapper<SchoolTeacherDto> {
}
