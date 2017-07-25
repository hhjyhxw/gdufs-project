package com.icloud.service.signed;

import com.icloud.common.dto.vo.signed.DataVo;
import com.icloud.common.dto.vo.signed.SignedVo;
import com.icloud.model.signed.Signed;
import com.icloud.model.user.User;
import com.icloud.service.BaseService;

/**
 * @filename      : SignedService.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月8日 下午5:51:01   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public interface SignedService extends BaseService<Signed>{

	DataVo signInRecord(int pageNo, int pageSize, User user);

	void signUp(Signed sign,User user);
	
	int selectSignedCountToday(String openid);

}
