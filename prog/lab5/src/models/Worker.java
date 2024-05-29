package models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

import lab5.Constants;
/** 
 * Class Worker
 */
public class Worker implements Comparable<Worker> {
    /*private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    public java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float salary; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.ZonedDateTime startDate; //Поле не может быть null
    private java.util.Date endDate; //Поле может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле может быть null
    */
    /**
     * Worker's id,
     * <p>Must be greater than zero,
     * <p>Must be unique,
     * <p>Value is generated automatically
     */
    private Integer id;

    /**
     * Worker's name,
     * <p>Can't be null,
     * <p>Can't be empty
     */
    private String name;

    /**
     * Worker's coordinates,
     * <p>Can't be null
     */
    private Coordinates coordinates;

    /**
     * Creation date of object,
     * <p>Can't be null,
     * <p>Value is generated automatically
     */
    private java.time.LocalDateTime creationDate;

    /**
     * Worker's salary,
     * <p>Can't be null,
     * <p>Must be greater than zero
     */
    private Float salary;

    /**
     * Start day of work,
     * <p>Can't be null
     */
    private java.time.ZonedDateTime startDate;

    /**
     * End day of work,
     * <p>Can't be null
     */
    private java.util.Date endDate;

    /**
     * Worker's status,
     * <p>Can't be null
     */
    private Status status;

    /**
     * Person of worker,
     * <p>Can't be null
     */
    private Organization organization;

    public Worker(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, Float salary, ZonedDateTime startDate, Date endDate, Status status, Organization organization) {
        //id = nextId++;
    	this.id= id;
        this.name = name;
        this.coordinates = coordinates;
        //creationDate = LocalDateTime.now();
        this.creationDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;		
        this.status = status;
        this.organization = organization;
    }
    /**
     * Method to get id
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Method to set id
     * @param id new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Float getSalary() {
        return salary;
    }
    public ZonedDateTime getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }


    public Status getStatus() {
        return status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Updates worker
     * @param worker
     */
    public void update(Worker worker){
        this.name = worker.name;
        this.coordinates = worker.coordinates;
        this.creationDate = worker.creationDate;
        this.salary = worker.salary;
        this.startDate = worker.startDate;
        this.endDate = worker.endDate;		
        this.status = worker.status;
        this.organization = worker.organization;
    }
    /**
     * Method to compare to workers
     * <p>Elements are compared by their salaries
     * @param worker the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */  
     
    @Override
    public int compareTo(Worker worker) {
		if(this.getSalary() > worker.getSalary()) {
            return 1;
        } else if (this.getSalary() < worker.getSalary()) {
            return -1;
        } else {
            return 0;
        }
    }

    public Worker copy() {
        return this;
    }

    public String toString() {
        return "Worker [id = " + id + "]:\n" +
                "\tname: '" + name + "\'\n" +

                "\tcoordinates:\n" +
                (Objects.isNull(coordinates) ? "\t\tnull" :
                "\t\tx: " + coordinates.getX() + "\n" +
                "\t\ty: " + coordinates.getY()) + "\n" +

                "\tcreationDate: " + creationDate.format(Constants.formatter) + "\n" +
                "\tsalary: " + salary + "\n" +
                "\tstartDate: " + startDate.toLocalDate().format(Constants.formatter) + "\n" +
                "\tendDate: " + (!Objects.isNull(endDate) ? endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Constants.formatter) : null) + "\n" +
                "\tstatus: " + status + "\n" +

                "\torganization:\n" +
                (Objects.isNull(organization) ? "\t\tnull" :
                "\t\tname: " + organization.getName() + "\n" +
                "\t\ttype: " + organization.getOrganizationType());
    }
}
