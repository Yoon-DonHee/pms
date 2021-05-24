package reservation.pms.common.converter;

import reservation.pms.common.enums.RadioEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RadioAttributeConverter implements AttributeConverter<RadioEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RadioEnum s) {
        if (RadioEnum.OPTION1.equals(s)) {
            return RadioEnum.OPTION1.getColValue();
        } else if (RadioEnum.OPTION2.equals(s)) {
            return RadioEnum.OPTION2.getColValue();
        } else if (RadioEnum.OPTION3.equals(s)) {
            return RadioEnum.OPTION3.getColValue();
        }
        return null;
    }

    @Override
    public RadioEnum convertToEntityAttribute(Integer code) {
        if (RadioEnum.OPTION1.getColValue() == code) {
            return RadioEnum.OPTION1;
        } else if (RadioEnum.OPTION2.getColValue() == code) {
            return RadioEnum.OPTION2;
        } else if (RadioEnum.OPTION3.getColValue() == code) {
            return RadioEnum.OPTION3;
        }
        return null;
    }
}
