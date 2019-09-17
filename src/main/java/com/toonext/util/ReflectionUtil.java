package com.toonext.util;


import com.toonext.api.IAppEntity;
import com.toonext.log.Lg;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionUtil {
    private static final String APP_CONST_PACKAGE_AND_CLASS = ".init.ModuleConst";
    private static final String DATA_CONST_PACKAGE_AND_CLASS = ".init.DataConst";

    public static Object getAppConstValue(String appName, String fieldName) {
        String className = appName.toLowerCase() + APP_CONST_PACKAGE_AND_CLASS;
        try {
            Class<?> c = Class.forName(className);
            return c.getField(fieldName).get(null);
        } catch (ClassNotFoundException e) {
            Lg.error("a class \"" + className + "\" has not been found");
        } catch (IllegalArgumentException e) {
            Lg.exception(e);
        } catch (IllegalAccessException e) {
            Lg.exception(e);
        } catch (NoSuchFieldException e) {
            Lg.error("there is no field \"" + fieldName + "\" in \"" + appName + "\"");
        } catch (SecurityException e) {
            Lg.exception(e);
        }
        return null;
    }

    public static Object getDataConstValue(String appName, String fieldName) {
        String className = appName.toLowerCase() + DATA_CONST_PACKAGE_AND_CLASS;
        try {
            Class<?> c = Class.forName(className);
            return c.getField(fieldName).get(null);
        } catch (ClassNotFoundException e) {
            Lg.error("a class \"" + className + "\" has not been found");
        } catch (IllegalArgumentException e) {
            Lg.exception(e);
        } catch (IllegalAccessException e) {
            Lg.exception(e);
        } catch (NoSuchFieldException e) {
            Lg.error("there is no field \"" + fieldName + "\" in \"" + appName + "\"");
        } catch (SecurityException e) {
            Lg.exception(e);
        }
        return null;
    }

    public static boolean isAppConstAvailable(String appName) {
        String className = appName.toLowerCase() + APP_CONST_PACKAGE_AND_CLASS;
        try {
            Class<?> c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            Lg.error("Class \"" + className + "\" has not been found");
            return false;
        }
        return true;
    }

    public static Object setSimpleValues(Object entity, Object obj, String... fields) {

        try {
            for (String f : fields) {
                Object value = PropertyUtils.getProperty(obj, f);
                if (value != null) {
                    BeanUtils.setProperty(entity, f, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Lg.exception(e);
        }

        return entity;
    }

    public static Object setSimpleValue(Object entity, Object value, String fieldName) {
        try {
            if (value != null) {
                BeanUtils.setProperty(entity, fieldName, value);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Lg.exception(e);
        }
        return entity;
    }

    public static Map<String, String> getAllProperties(Object someObject) {
        Map<String, String> allProps = new HashMap<String, String>();
        for (Field field : someObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(someObject);
                if (value != null) {
                    allProps.put(field.getName(), value.toString());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                Lg.exception(e);
            }

        }
        return allProps;
    }


    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        return getAllFields(fields, type);
    }

    public static List<Field> getAllFields(String type) {
        try {
            Class<?> clazz = Class.forName(type);
            List<Field> fields = new ArrayList<>();
            return getAllFields(fields, clazz);
        } catch (ClassNotFoundException e) {
            Lg.exception(e);
            return null;
        }
    }


    public static List<Method> getAllMethods(List<Method> m, Class<?> type) {
        m.addAll(Arrays.asList(type.getMethods()));
        if (type.getSuperclass() != null) {
            m = getAllMethods(m, type.getSuperclass());
        }
        return m;
    }

    public static <T extends IAppEntity> T extractSimpleInstance(T entityObj) {
        try {
            Class<T> type = (Class<T>) entityObj.getClass();
            T entity = (T) getEmptyInstance(type);
            entity.setId(entityObj.getId());
            entity.setTitle(entityObj.getTitle());
            return entity;
        } catch (Exception e) {
            return entityObj;
        }
    }

    public static <T extends IAppEntity> T getEmptyInstance(Class<T> type) {
        try {
            T entity = type.newInstance();
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            Lg.exception(e);
            return null;
        } catch (Exception e) {
            return null;
        }
    }


}
