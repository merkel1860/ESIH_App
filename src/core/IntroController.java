package core;

import core.utils.Cxo;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "introController")
@SessionScoped

public class IntroController  implements Serializable {


    public IntroController() {
        init();
    }

    public void init(){
        Cxo.initConnection();
        Cxo.createDBSchema();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "App data successfully loaded",null);
        FacesContext.getCurrentInstance().addMessage(null,facesMsg);

    }
}
