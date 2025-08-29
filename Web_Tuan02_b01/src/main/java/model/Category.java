package model;

import jakarta.servlet.http.HttpServlet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category  {
    private int cateid;
    private String catename;
    private String icon;
    public Category(){
    }
}
