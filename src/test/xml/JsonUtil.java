package test.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 */
public class JsonUtil {

	/**
	 * 将json字符串转换成泛型的对象
	 * @param jsonString  json字符串
	 * @param c 泛型的对象
	 * @return 泛型的对象
	 * @throws ClassCastException
	 * @author 
	 * @date 
	 */
	public static <T> T jsonStringToObject(String jsonString, Class<T> c){
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonString, c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将json字符串转换成泛型的集合对象
	 * @param jsonString json字符串
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类 
	 * @return
	 * @author 
	 * @param <T>
	 * @date
	 */
	public static <T> T jsonStringToCollection(String jsonString,Class<T> collectionClass, Class<?>... elementClasses){
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
			return mapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param obj Object
	 * @param isEnableFeature
	 * @return
	 */
	public static String objectToJsonString(Object obj,boolean isEnableFeature){
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(isEnableFeature){
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
			}else{
				mapper.disable(SerializationFeature.INDENT_OUTPUT);		
			}
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将对象转换成json字符串
	 * @param obj Object
	 * @return String
	 * @author 
	 * @date
	 */
	public static String objectToJsonString(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
