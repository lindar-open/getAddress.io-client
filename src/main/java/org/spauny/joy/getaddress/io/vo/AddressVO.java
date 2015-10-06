package org.spauny.joy.getaddress.io.vo;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author iulian
 */
@Data
public class AddressVO implements Serializable {
    private static final long serialVersionUID = 18461328974512456L;
    
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String locality;
    private String city;
    private String county;
}
