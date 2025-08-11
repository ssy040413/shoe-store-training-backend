package com.xys.service.impl;

import com.xys.entity.Types;
import com.xys.mapper.TypesMapper;
import com.xys.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 类型业务逻辑实现类
 */
@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypesMapper typesMapper;

    @Override
    public List<Types> getAllTypes() {
        try {
            return typesMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Types getTypeById(Integer typeId) {
        try {
            return typesMapper.findById(typeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addType(Types types) {
        try {
            if (types.getStatus() == null) {
                types.setStatus("1");
            }
            int result = typesMapper.insert(types);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateType(Types types) {
        try {
            int result = typesMapper.update(types);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteType(Integer typeId) {
        try {
            int result = typesMapper.deleteById(typeId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Types> getActiveTypes() {
        try {
            return typesMapper.findActiveTypes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Types addTypeAndReturn(Types types) {
        try {
            if (types.getStatus() == null) {
                types.setStatus("1");
            }
            int result = typesMapper.insert(types);
            if (result > 0) {
                return typesMapper.findById(types.getTypeId());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Types updateTypeAndReturn(Types types) {
        try {
            int result = typesMapper.update(types);
            if (result > 0) {
                return typesMapper.findById(types.getTypeId());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}