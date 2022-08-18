/**
 * Copyright (c) 2022 aoshiguchen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fun.asgc.neutrino.proxy.server.dal;

import fun.asgc.neutrino.core.annotation.Component;
import fun.asgc.neutrino.core.annotation.Param;
import fun.asgc.neutrino.core.db.annotation.Delete;
import fun.asgc.neutrino.core.db.annotation.ResultType;
import fun.asgc.neutrino.core.db.annotation.Select;
import fun.asgc.neutrino.core.db.annotation.Update;
import fun.asgc.neutrino.core.db.mapper.SqlMapper;
import fun.asgc.neutrino.core.db.page.Page;
import fun.asgc.neutrino.proxy.server.controller.req.PortMappingListReq;
import fun.asgc.neutrino.proxy.server.controller.res.PortMappingListRes;
import fun.asgc.neutrino.proxy.server.dal.entity.PortMappingDO;

import java.util.Set;

/**
 *
 * @author: aoshiguchen
 * @date: 2022/8/8
 */
@Component
public interface PortMappingMapper extends SqlMapper {

	@ResultType(PortMappingListRes.class)
	@Select("select * from port_mapping")
	void page(Page page, PortMappingListReq req);

	void add(PortMappingDO portMappingDO);

	void update(PortMappingDO portMappingDO);

	@Select("select * from port_mapping where id = ?")
	PortMappingDO findById(Integer id);

	@Update("update `port_mapping` set enable = :enable where id = :id")
	void updateEnableStatus(@Param("id") Integer id, @Param("enable") Integer enable);

	@Delete("delete from `port_mapping` where id = ?")
	void delete(Integer id);

	@Select("select * from port_mapping where server_port = ?")
	PortMappingDO findByPort(Integer port);

	@Select("select * from port_mapping where server_port = :port and id not in (:excludeIds)")
	PortMappingDO findByPort(@Param("port") Integer port, @Param("excludeIds") Set<Integer> excludeIds);
}