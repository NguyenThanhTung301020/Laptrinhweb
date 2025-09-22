package thanhtung.models;

import java.io.Serializable;
import java.sql.Date;

public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;
    private String image;
    private Date createdDate;

    public CategoryModel() {
        super();
    }

    public CategoryModel(int id, String name, String description, String image, Date createdDate) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.createdDate = createdDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    @Override
    public String toString() {
        return "CategoryModel [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", createdDate=" + createdDate + "]";
    }
}