package com.icloud.service.equipment;

import com.icloud.common.dto.vo.equipment.DataVo;
import com.icloud.model.equipment.Equipment;
import com.icloud.model.user.User;
import com.icloud.service.BaseService;

/**
 * @filename      : EquipmentService.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月8日 下午5:46:26   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public interface EquipmentService extends BaseService<Equipment>{

	DataVo mySubmitList(int pageNo, int pageSize, User user);

	void submitDisabled(Equipment equipment);

}
