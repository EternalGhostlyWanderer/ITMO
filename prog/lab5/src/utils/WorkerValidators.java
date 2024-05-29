package utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import exceptions.InvalidDataException;
import models.Coordinates;
import models.Organization;
import models.OrganizationType;
import models.Status;
import models.Worker;

/**
 * Class which contains validators for all fields of Worker
 * <p>All validators realize constraints given by task
 */
public class WorkerValidators {
    public static Validator<Worker> workerValidator = new Validator<Worker>() {
    	 @Override
         public void validate(Worker value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Worker can't be empty!");
             idValidator.validate(value.getId());
             nameValidator.validate(value.getName());
             coordinatesValidator.validate(value.getCoordinates());
             creationDateValidator.validate(value.getCreationDate());
             salaryValidator.validate(value.getSalary());
             startDateValidator.validate(value.getStartDate());
             endDateValidator.validate(value.getEndDate());
             statusValidator.validate(value.getStatus());
             organizationValidator.validate(value.getOrganization());
         }
     };
     public static Validator<Integer> idValidator = new Validator<Integer>() {
         @Override
         public void validate(Integer value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Id can't be empty!");
             if(value <= 0) throw new InvalidDataException("Id must be greater than zero!");
         }
     };
     public static Validator<String> nameValidator = new Validator<String>() {
         @Override
         public void validate(String value) throws InvalidDataException {
             if(value == null || value.isEmpty()) throw new InvalidDataException("Name can't be empty!");
             if(value.contains(" ")) throw new InvalidDataException("Name can't contain spaces!");
         }
     };
     public static Validator<Coordinates> coordinatesValidator = new Validator<Coordinates>() {
         @Override
         public void validate(Coordinates value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Coordinates can't be empty!");
             xValidator.validate(value.getX());
             yValidator.validate(value.getY());
         }
     };
     public static Validator<Long> xValidator = new Validator<Long>() {
         @Override
         public void validate(Long value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("X can't be empty!");
             if(value < -418) throw new InvalidDataException("x coordinate min value is -418");
         }
     };
     public static Validator<Double> yValidator = new Validator<Double>() {
         @Override
         public void validate(Double value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Y can't be empty!");
             if(value < -287) throw new InvalidDataException("y coordinate min value is -287");
         }
     };
     public static Validator<ZonedDateTime> startDateValidator = new Validator<ZonedDateTime>() {
         @Override
         public void validate(ZonedDateTime value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Start date can't be empty!");
         }
     };
     public static Validator<Date> endDateValidator = new Validator<Date>() {
         @Override
         public void validate(Date value) throws InvalidDataException {
             if(value == null) return;
         }
     };
     public static Validator<LocalDateTime> creationDateValidator = new Validator<LocalDateTime>() {
         @Override
         public void validate(LocalDateTime value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Creation date can't be empty!");
         }
     };
     public static Validator<Float> salaryValidator = new Validator<Float>() {
         @Override
         public void validate(Float value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Salary can't be empty!");
             if(value <= 0) throw new InvalidDataException("Salary must be greater than zero!");
         }
     };
     public static Validator<Status> statusValidator = new Validator<Status>() {
         @Override
         public void validate(Status value) throws InvalidDataException {
             if(value == null) return;
         }
     };
     public static Validator<Organization> organizationValidator = new Validator<Organization>() {
         @Override
         public void validate(Organization value) throws InvalidDataException {
             if(value == null) return;
             organizationNameValidator.validate(value.getName());
             organizationTypeValidator.validate(value.getOrganizationType());
         }
     };
     public static Validator<String> organizationNameValidator = new Validator<String>() {
         @Override
         public void validate(String value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Organization name can't be empty!");
             if(value.length() >= 1654) throw new InvalidDataException("Organization name must be greater than zero!");
         }
     };
     public static Validator<OrganizationType> organizationTypeValidator = new Validator<OrganizationType>() {
         @Override
         public void validate(OrganizationType value) throws InvalidDataException {
             if(value == null) throw new InvalidDataException("Organization type can't be empty!");
         }
     };
 }