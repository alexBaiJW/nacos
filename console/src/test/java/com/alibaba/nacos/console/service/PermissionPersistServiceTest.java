/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.console.service;

import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.config.server.auth.PermissionPersistServiceTmp;
import com.alibaba.nacos.config.server.modules.entity.Permissions;
import com.alibaba.nacos.console.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangshun
 * @version $Id: PermissionPersistServiceTest.java,v 0.1 2020年06月06日 14:58 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionPersistServiceTest extends BaseTest {

    private Permissions permissions;

    @Before
    public void before() {
        String data = readClassPath("test-data/permissions.json");
        permissions = JacksonUtils.toObj(data, Permissions.class);
    }

    @Autowired
    private PermissionPersistServiceTmp permissionPersistServiceTmp;


    @Test
    public void getPermissionsTest() {
        Page<Permissions> page = permissionPersistServiceTmp.getPermissions(permissions.getRole(), 0, 10);
        Assert.assertNotNull(page.getContent());
        Assert.assertTrue(page.getContent().size() > 0);
    }

    @Test
    public void addPermissionTest() {
        permissionPersistServiceTmp.addPermission(permissions.getRole(), permissions.getResource(), permissions.getAction());
    }


    @Test
    public void deletePermissionTest() {
        permissionPersistServiceTmp.deletePermission(permissions.getRole(), permissions.getResource(), permissions.getAction());
    }
}
