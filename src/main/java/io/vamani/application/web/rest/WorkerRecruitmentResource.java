package io.vamani.application.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.BankMaster;
import io.vamani.application.domain.DepartmentMaster;
import io.vamani.application.domain.DesignationMaster;
import io.vamani.application.domain.WorkerRecruitment;
import io.vamani.application.mobile.WorkerRecruitmentMobile;
import io.vamani.application.model.Master;
import io.vamani.application.model.TrailMockSearchOperation;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.WorkerRecruitmentRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * REST controller for managing WorkerRecruitment.
 */
@RestController
@RequestMapping("/api")
public class WorkerRecruitmentResource {

    private final Logger log = LoggerFactory.getLogger(WorkerRecruitmentResource.class);

    private static final String ENTITY_NAME = "workerRecruitment";

    private final WorkerRecruitmentRepository workerRecruitmentRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public WorkerRecruitmentResource(WorkerRecruitmentRepository workerRecruitmentRepository) {
        this.workerRecruitmentRepository = workerRecruitmentRepository;
    }

    /**
     * POST  /worker-recruitments : Create a new workerRecruitment.
     *
     * @param @workerRecruitment the workerRecruitment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerRecruitment, or with status 400 (Bad Request) if the workerRecruitment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/worker-recruitments", consumes = {"multipart/form-data"})
    public ResponseEntity<WorkerRecruitment> createWorkerRecruitment(@RequestParam(required = false) MultipartFile file, String id, String name, String aadharNo, String dob, String fatherName, String address, String panNo, String bankBranch, String bankAccNo, String bankMaster, String designationMaster, String corespondAddress, String departmentMaster) throws URISyntaxException, IOException, ParseException {
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerRecruitment workerRecruitment = new WorkerRecruitment();
        workerRecruitment.setAadharNo(aadharNo);
        workerRecruitment.setName(name);
        workerRecruitment.setFatherName(fatherName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = dateFormat.parse(dob);
        workerRecruitment.setDob(dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        workerRecruitment.setAddress((address != null ? address.toUpperCase() : address));
        workerRecruitment.setPanNo((panNo != null ? panNo.toUpperCase() : panNo));
        workerRecruitment.setBankBranch((bankBranch != null ? bankBranch.toUpperCase() : bankBranch));
        workerRecruitment.setBankAccNo(bankAccNo);
        workerRecruitment.setStatus("E");
        workerRecruitment.setCorespondAddress((corespondAddress != null ? corespondAddress.toUpperCase() : corespondAddress));

        BankMaster bank = new BankMaster();
        bank.setId(Long.parseLong(bankMaster));
        workerRecruitment.setBankMaster(bank);

        DepartmentMaster department = new DepartmentMaster();
        department.setId(Long.parseLong(departmentMaster));
        workerRecruitment.setDepartmentMaster(department);

        DesignationMaster designation = new DesignationMaster();
        designation.setId(Long.parseLong(designationMaster));
        workerRecruitment.setDesignationMaster(designation);

        workerRecruitment.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        workerRecruitment.setCreatedDate(Instant.now());
        workerRecruitment.setFactoryCode(employeeView.getSubSname().trim());
        if (workerRecruitment.getId() != null) {
            throw new BadRequestAlertException("A new workerRecruitment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerRecruitment result = workerRecruitmentRepository.save(workerRecruitment);
        if(file!=null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = result.getId() + extn;
            result.setFileName(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/profile/" + fileName);
            Files.write(path, bytes);
            result = workerRecruitmentRepository.save(result);
        }
        return ResponseEntity.created(new URI("/api/worker-recruitments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-recruitments : Updates an existing workerRecruitment.
     *
     * @param @workerRecruitment the workerRecruitment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerRecruitment,
     * or with status 400 (Bad Request) if the workerRecruitment is not valid,
     * or with status 500 (Internal Server Error) if the workerRecruitment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException
     */
    @PostMapping(value = "/worker-recruitments-update", consumes = {"multipart/form-data"})
    public ResponseEntity<WorkerRecruitment> updateWorkerRecruitment(@RequestParam(required = false) MultipartFile file, String id, String name, String aadharNo, String dob, String fatherName, String address, String panNo, String bankBranch, String bankAccNo, String bankMaster, String designationMaster, String corespondAddress, String departmentMaster) throws URISyntaxException, IOException, ParseException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerRecruitment workerRecruitment = workerRecruitmentRepository.findById(Long.parseLong(id)).orElse(null);
        workerRecruitment.setName(name);
        workerRecruitment.setFatherName(fatherName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = dateFormat.parse(dob);
        workerRecruitment.setDob(dobDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        workerRecruitment.setAddress((address != null ? address.toUpperCase() : address));
        workerRecruitment.setPanNo((panNo != null ? panNo.toUpperCase() : panNo));
        workerRecruitment.setBankBranch((bankBranch != null ? bankBranch.toUpperCase() : bankBranch));
        workerRecruitment.setBankAccNo(bankAccNo);
        workerRecruitment.setCorespondAddress(corespondAddress.trim().toUpperCase());

        BankMaster bank = new BankMaster();
        bank.setId(Long.parseLong(bankMaster));
        workerRecruitment.setBankMaster(bank);

        DepartmentMaster department = new DepartmentMaster();
        department.setId(Long.parseLong(departmentMaster));
        workerRecruitment.setDepartmentMaster(department);

        DesignationMaster designation = new DesignationMaster();
        designation.setId(Long.parseLong(designationMaster));
        workerRecruitment.setDesignationMaster(designation);

        workerRecruitment.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        workerRecruitment.setLastUpdatedDate(Instant.now());
        if (workerRecruitment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerRecruitment result = workerRecruitmentRepository.save(workerRecruitment);
        if(file!=null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = result.getId() + extn;
            result.setFileName(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/profile/" + fileName);
            Files.write(path, bytes);
            result = workerRecruitmentRepository.save(result);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerRecruitment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-recruitments : get all the workerRecruitments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerRecruitments in body
     */
    @GetMapping("/worker-recruitments")
    public ResponseEntity<List<WorkerRecruitment>> getAllWorkerRecruitments(Pageable pageable) {
        log.debug("REST request to get a page of WorkerRecruitments");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Page<WorkerRecruitment> page = workerRecruitmentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-recruitments");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * POST  /employee-views-custom : get all the employeeViews.
     *
     * @param search the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @PostMapping("/worker-recruitments-custom")
    @Timed
    public ResponseEntity<List<WorkerRecruitment>>getAllWorkerRecruitmentsCustom(@Valid @RequestBody TrailMockSearchOperation search) {
        log.debug("REST request to get a page of WorkerRecruitments");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String name = "%";
        String aadharNo = "%";
        String department = "%";
        String designation = "%";

        if (search.getName() != null) {
            name = "%" + search.getName().toUpperCase() + "%";
        }
        if (search.getAadharNo() != null) {
            aadharNo = "%" + search.getAadharNo() + "%";
        }
        if (search.getDepartment() != null) {
            department = "%" + search.getDepartment().toUpperCase() + "%";
        }
        if (search.getDesignation() != null) {
            designation = "%" + search.getDesignation().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        Page<WorkerRecruitment> page = workerRecruitmentRepository.findAllByFilter(name,aadharNo, department, designation, employeeView.getSubSname().trim(),search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trail-mock-operations-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-recruitments/:id : get the "id" workerRecruitment.
     *
     * @param id the id of the workerRecruitment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerRecruitment, or with status 404 (Not Found)
     */
    @GetMapping("/worker-recruitments/{id}")
    public ResponseEntity<WorkerRecruitment> getWorkerRecruitment(@PathVariable Long id) {
        log.debug("REST request to get WorkerRecruitment : {}", id);
        Optional<WorkerRecruitment> workerRecruitment = workerRecruitmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerRecruitment);
    }

    /**
     * DELETE  /worker-recruitments/:id : delete the "id" workerRecruitment.
     *
     * @param id the id of the workerRecruitment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-recruitments/{id}")
    public ResponseEntity<Void> deleteWorkerRecruitment(@PathVariable Long id) {
        log.debug("REST request to delete WorkerRecruitment : {}", id);
        workerRecruitmentRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * DELETE  /worker-recruitments/:id : delete the "id" workerRecruitment.
     *
     * @param id the id of the workerRecruitment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @PostMapping("/fetch-aadhar")
    public ResponseEntity<WorkerRecruitmentMobile> fetchAadhar(@RequestBody Master master) throws ParseException, FileNotFoundException, IOException {
        log.debug("REST request to delete WorkerRecruitment : {}", master);
        WorkerRecruitmentMobile workerRecruitment = new WorkerRecruitmentMobile();
        if (master.getId() != null && master.getId().contains("<?xml")) {
            master.setId(master.getId().substring(master.getId().indexOf(">")+1, master.getId().length()));
            JSONObject jsonObject = XML.toJSONObject(master.getId());
            Map<String, String> map = new ObjectMapper().readValue(jsonObject.get("PrintLetterBarcodeData").toString(), HashMap.class);
            jsonObject = new JSONObject(map);
            //Aadhar
            if (jsonObject.has("uid")) {
                if(master.getDesc() != null && master.getDesc().equalsIgnoreCase(jsonObject.get("uid").toString())) {
                    workerRecruitment.setAadharNo(jsonObject.get("uid").toString());
                    workerRecruitment.setExist(true);
                } else {
                    workerRecruitment.setExist(false);
                    workerRecruitment.setErrorMessage("Aadhar no and scanned data mismatched!");
                    return ResponseEntity.ok().body(workerRecruitment);
                }
            }

            //Father
            if (jsonObject.has("co")) {
                workerRecruitment.setFatherName(jsonObject.get("co").toString());
            } else if (jsonObject.has("careOf")) {
                workerRecruitment.setFatherName(jsonObject.get("careOf").toString());
            }

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");
            //DOB
            if (jsonObject.has("dob")) {
                String dob = jsonObject.get("dob").toString();
                if (dob.lastIndexOf('-') != -1) {
                    if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 2) {
                        workerRecruitment.setDob(format.format(format2.parse(dob)));
                    } else if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 4) {
                        workerRecruitment.setDob(dob);
                    }
                } else if (dob.lastIndexOf('/') != -1) {
                    workerRecruitment.setDob(format.format(format3.parse(dob)));
                }
            } else if (jsonObject.has("dateOfBirth")) {
                String dob = jsonObject.get("dateOfBirth").toString();
                if (dob.lastIndexOf('-') != -1) {
                    if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 2) {
                        workerRecruitment.setDob(format.format(format2.parse(dob)));
                    } else if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 4) {
                        workerRecruitment.setDob(dob);
                    }
                } else if (dob.lastIndexOf('/') != -1) {
                    workerRecruitment.setDob(format.format(format3.parse(dob)));
                }
            }

            //Name
            if (jsonObject.has("name")) {
                workerRecruitment.setName(jsonObject.get("name").toString());
            }

            //Address
            String address = "";
            if (jsonObject.has("house")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("house").toString();
                } else {
                    address += jsonObject.get("house").toString();
                }
            }
            if (jsonObject.has("street")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("street").toString();
                } else {
                    address += jsonObject.get("street").toString();
                }
            }
            if (jsonObject.has("loc")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("loc").toString();
                } else {
                    address += jsonObject.get("loc").toString();
                }
            }
            if (jsonObject.has("po")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("po").toString();
                } else {
                    address += jsonObject.get("po").toString();
                }
            }
            if (jsonObject.has("vtc")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("vtc").toString();
                } else {
                    address += jsonObject.get("vtc").toString();
                }
            }
            if (jsonObject.has("dist")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("dist").toString();
                } else {
                    address += jsonObject.get("dist").toString();
                }
            }
            if (jsonObject.has("state")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("state").toString();
                } else {
                    address += jsonObject.get("state").toString();
                }
            }
            if (jsonObject.has("pc")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("pc").toString();
                } else {
                    address += jsonObject.get("pc").toString();
                }
            }
            if (jsonObject.has("building")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("building").toString();
                } else {
                    address += jsonObject.get("building").toString();
                }
            }
            if (jsonObject.has("locality")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("locality").toString();
                } else {
                    address += jsonObject.get("locality").toString();
                }
            }
            if (jsonObject.has("poName")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("poName").toString();
                } else {
                    address += jsonObject.get("poName").toString();
                }
            }
            if (jsonObject.has("vtcName")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("vtcName").toString();
                } else {
                    address += jsonObject.get("vtcName").toString();
                }
            }
            if (jsonObject.has("subDistrictName")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("subDistrictName").toString();
                } else {
                    address += jsonObject.get("subDistrictName").toString();
                }
            }
            if (jsonObject.has("districtName")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("districtName").toString();
                } else {
                    address += jsonObject.get("districtName").toString();
                }
            }
            if (jsonObject.has("stateName")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("stateName").toString();
                } else {
                    address += jsonObject.get("stateName").toString();
                }
            }
            if (jsonObject.has("pincode")) {
                if (address.length() > 0) {
                    address += "," + jsonObject.get("pincode").toString();
                } else {
                    address += jsonObject.get("pincode").toString();
                }
            }
            workerRecruitment.setAddress(address);
        } else {
            BigInteger bigInteger = new BigInteger(master.getId());
            byte[] bytes = bigInteger.toByteArray();
            String fileName = new Date().getTime() + "";
            OutputStream os = new FileOutputStream(applicationProperties.getUploadPath() + fileName + ".gz");
            os.write(bytes);
            os.close();
            String outputFile = getFileName(new File(applicationProperties.getUploadPath() + fileName + ".gz"), applicationProperties.getUploadPath());

            // zipped input
            File tarFile = deCompressGZipFile(new File(applicationProperties.getUploadPath() + fileName + ".gz"), new File(outputFile));
            List<String> arrayList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(applicationProperties.getUploadPath() + tarFile.getName()));

            String st;
            while ((st = br.readLine()) != null) {
                arrayList.add(st);
            }
            String strCurrentLine = "";
            for(String arr : arrayList) {
                strCurrentLine +=arr;
            }
            strCurrentLine = new String(strCurrentLine.getBytes("ISO-8859-1"));
            String[] array = strCurrentLine.split("\\?");

            if (array[1] != null && array[1].length() > 0) {
                String validateChar = array[1].toString().substring(0, 4);
                String inputValidate = master.getDesc().substring(master.getDesc().length() - 4);
                if (validateChar != null && validateChar.equalsIgnoreCase(inputValidate)) {
                    workerRecruitment.setAadharNo(master.getDesc());
                    workerRecruitment.setExist(true);
                } else {
                    workerRecruitment.setExist(false);
                    workerRecruitment.setErrorMessage("Aadhar no and scanned data mismatched!");
                    return ResponseEntity.ok().body(workerRecruitment);
                }
            }

            if(array[2] != null && array[2].length()>0){
                workerRecruitment.setName(array[2]);
            }
            if(array[5] != null && array[5].length()>0){
                workerRecruitment.setFatherName(array[5]);
            }
            if(array[3] != null && array[3].length()>0) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");
                //DOB
                String dob = array[3];
                if (dob.lastIndexOf('-') != -1) {
                    if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 2) {
                        workerRecruitment.setDob(format.format(format2.parse(dob)));
                    } else if (dob.substring(dob.lastIndexOf('-') + 1, dob.length()).length() == 4) {
                        workerRecruitment.setDob(dob);
                    }
                } else if (dob.lastIndexOf('/') != -1) {
                    workerRecruitment.setDob(format.format(format3.parse(dob)));
                }
            }
            //Address
            String address = "";
            if (array[8] != null && array[8].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[8];
                } else {
                    address += array[8];
                }
            }
            if (array[13] != null && array[13].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[13];
                } else {
                    address += array[13];
                }
            }
            if (array[7] != null && array[7].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[7];
                } else {
                    address += array[7];
                }
            }
            if (array[9] != null && array[9].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[9];
                } else {
                    address += array[9];
                }
            }
            if (array[11] != null && array[11].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[11];
                } else {
                    address += array[11];
                }
            }
            /*if (array[15] != null && array[15].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[15];
                } else {
                    address += array[15];
                }
            }
            if (array[14] != null && array[14].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[14];
                } else {
                    address += array[14];
                }
            }*/
            if (array[6] != null && array[6].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[6];
                } else {
                    address += array[6];
                }
            }
            if (array[12] != null && array[12].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[12];
                } else {
                    address += array[12];
                }
            }
            if (array[10] != null && array[10].length()>0) {
                if (address.length() > 0) {
                    address += "," + array[10];
                } else {
                    address += array[10];
                }
            }
            workerRecruitment.setAddress(address);
            br.close();
        }
        return ResponseEntity.ok().body(workerRecruitment);
    }

    /**
     * Method to decompress a gzip file
     * @param gZippedFile
     * @param newFile
     * @throws IOException
     */
    private static File deCompressGZipFile (File gZippedFile, File tarFile) throws IOException {
        FileInputStream fis = new FileInputStream(gZippedFile);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);

        FileOutputStream fos = new FileOutputStream(tarFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gZIPInputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

        fos.close();
        gZIPInputStream.close();
        return tarFile;

    }

    /**
     * This method is used to get the tar file name from the gz file
     * by removing the .gz part from the input file
     * @param inputFile
     * @param outputFolder
     * @return
     */
    private static String getFileName(File inputFile, String outputFolder){
        return outputFolder + File.separator +
            inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
    }

    /**
     *
     * @param tarFile
     * @param destFile
     * @throws IOException
     */
    private static void unTarFile(File tarFile, File destFile) throws IOException{
        FileInputStream fis = new FileInputStream(tarFile);
        TarArchiveInputStream tis = new TarArchiveInputStream(fis);
        TarArchiveEntry tarEntry = null;

        // tarIn is a TarArchiveInputStream
        while ((tarEntry = tis.getNextTarEntry()) != null) {
            File outputFile = new File(destFile + File.separator + tarEntry.getName());

            if(tarEntry.isDirectory()){

                System.out.println("outputFile Directory ---- "
                    + outputFile.getAbsolutePath());
                if(!outputFile.exists()){
                    outputFile.mkdirs();
                }
            }else{
                //File outputFile = new File(destFile + File.separator + tarEntry.getName());
                System.out.println("outputFile File ---- " + outputFile.getAbsolutePath());
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(outputFile);
                IOUtils.copy(tis, fos);
                fos.close();
            }
        }
        tis.close();
    }
}
