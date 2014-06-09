package pl.bb.broker.brokerreservations.beans.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 05.06.14
 * Time: 00:14
 * To change this template use File | Settings | File Templates.
 */

@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
             Object value) throws ValidatorException {
        UIInput sd = (UIInput) component.getAttributes().get("arrival");
        Date arrival = (Date) sd.getValue();
        Date departure = (Date) value;

        if(!arrival.before(departure)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne daty - przyjazd musi być przed odjazdem!", null);
            throw new ValidatorException(msg);
        }
        if(arrival.before(new Date())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne daty - przyjazd może być najwcześniej jutro!", null);
            throw new ValidatorException(msg);
        }
    }
}
