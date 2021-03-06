package models.RT;


import models.DATA;
import models.enums.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by F.Arian on 02.12.17.
 */
public class Film {
    private  String id;
    private static int instanceCounter = 0;
    private NAME filmName;
    private List<TYPE> ndtType;
    private TYPE type;
    private MODEL model;
    private SIZE size;
    private LOCATION location;
    private double amountFilm;
    private boolean filmIsExpirt;
    /**
     * list all gama ray and xRay for film
     */
    private List<ISOTOPETYPE> isotopetypes = new ArrayList<>();

    /**
     * Films Types ,if is from list will be set best contrast
     */
    private List<TYPE> filmsTypes = new ArrayList<>();
    /**
     * a isotope for Film , if is from list will be set best contrast
     */
    private ISOTOPETYPE isotopetype = ISOTOPETYPE.IRIDIUM_192;
    private double developTemperature_C;
    private double developImmersionTime_S;
    private double contrast;
    private double relativeExposureFactors;
    private String expiryDate;
    private String date;
    private int numberOfBoxSheets;
    private int weightFilm;
    private int lengthFilm;
    private int widthFilm;
    private String featuresandMajorApplications;
    private double base_Fog;
    private String serialNumber;
    private int counter = 0;


    public Film(NAME filmName, TYPE filmType, MODEL gamaRayOrXraySheetOrRoll, SIZE size) {
        this.setFilmName(filmName);
        this.setType(filmType);
        this.setModel(gamaRayOrXraySheetOrRoll);
        this.setSize(size);
        this.amountFilm();
        this.setAmountFilm(getLengthFilm());
        this.setWeightFilm(-1);
        this.setExpiryDate(DATA.changeDate(0, 0, 2));
        this.setLocation(LOCATION.CENTRAL);
        this.filmsTypes = new ArrayList<>();
        this.isotopetypes = new ArrayList<>();
        this.ndtType = new ArrayList<>();
        this.ndtType.add(TYPE.RT);
        this.ndtType.add(TYPE.D_ROOM);
        this.setFilmIsexpirt(false);
        this.setBase_Fog(-1);

    }

    /**
     * default constructor
     */
    public Film() {

    }

    public void updateFilm() {
        setFilmName(getFilmName());
        setType(getType());
        setModel(getModel());
        setSize(getSize());
        amountFilm();
        setAmountFilm(getLengthFilm());
        setWeightFilm(getWidthFilm());
        setDate(getDate());
        setExpiryDate(getExpiryDate());
        setBase_Fog(getBase_Fog());
        this.setSerialNumber(DATA.creatId("-" + getFilmName().name().toString()));
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;
        if (getIsotopetype() != null) {
            relativeExposureFactors();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public  String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * should be Isotope set and than call this Method
     * set Relative Exposure Factors
     */
    private void relativeExposureFactors() {
        this.setDevelopTemperature_C(-1);
        this.setDevelopImmersionTime_S(-1);
        this.setRelativeExposureFactors(-1);
        this.setFeaturesandMajorApplications("NOT SET");
        this.setContrast(-1);

        if (getFilmName().equals(NAME.KODAK)) {
            this.contrastUpdate(480, 26, getType());
            this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
            this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
            this.isotopetypes.add(ISOTOPETYPE.X_Ray220KV);
            this.isotopetypes.add(ISOTOPETYPE.X_Ray120KV);
            this.filmsTypes.add(TYPE.AA400);
            this.filmsTypes.add(TYPE.T200);
            this.filmsTypes.add(TYPE.MX125);
            this.filmsTypes.add(TYPE.M100);
            this.filmsTypes.add(TYPE.DR50);

            switch (getType()) {

                case DR50:
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60) ||
                            isotopetype.equals(ISOTOPETYPE.IRIDIUM_192) ||
                            isotopetype.equals(ISOTOPETYPE.X_Ray120KV)) {
                        this.setRelativeExposureFactors(9.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray220KV)) {
                        this.setContrast(4.8);
                        double time = this.getDevelopImmersionTime_S();
                        double temp = this.getDevelopTemperature_C();
                        if (time == 360 && temp == 30) {
                            this.setContrast(4.7);
                        }
                        if (time == 480 && temp == 28) {
                            this.setContrast(4.5);
                        }
                        this.setRelativeExposureFactors(7.2);
                    }

                    break;
                case M100:
                    this.setContrast(4.2);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray120KV)) {
                        this.setRelativeExposureFactors(4.1);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray220KV)) {
                        this.setRelativeExposureFactors(4.2);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(5.4);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(6.3);
                    }
                    break;
                case MX125:
                    this.setContrast(2.8);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray120KV)) {
                        this.setRelativeExposureFactors(2.9);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray220KV)) {
                        this.setRelativeExposureFactors(2.8);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(3.1);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(3.3);
                    }
                    break;
                case T200:
                    this.setContrast(1.7);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray120KV)) {
                        this.setRelativeExposureFactors(1.6);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray220KV)) {
                        this.setRelativeExposureFactors(1.7);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192) ||
                            isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(1.9);
                    }
                    break;
                case AA400:
                    this.setContrast(1.0);
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60) ||
                            isotopetype.equals(ISOTOPETYPE.IRIDIUM_192) ||
                            isotopetype.equals(ISOTOPETYPE.X_Ray120KV) ||
                            isotopetype.equals(ISOTOPETYPE.X_Ray120KV)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                default:
                    this.setRelativeExposureFactors(-1);
                    break;
            }
        }
        if (getFilmName().equals(NAME.AGFA)) {
            this.contrastUpdate(480, 28, getType());
            this.setWeightFilm(11);
            this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
            this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
            this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
            this.isotopetypes.add(ISOTOPETYPE.X_Ray200KV);
            this.isotopetypes.add(ISOTOPETYPE.X_Ray100KV);
            this.isotopetypes.add(ISOTOPETYPE.LINAC_8MeV);
            this.filmsTypes.add(TYPE.STRUCTURIX_D2);
            this.filmsTypes.add(TYPE.STRUCTURIX_D3_sc);
            this.filmsTypes.add(TYPE.STRUCTURIX_D3);
            this.filmsTypes.add(TYPE.STRUCTURIX_D4);
            this.filmsTypes.add(TYPE.STRUCTURIX_D5);
            this.filmsTypes.add(TYPE.STRUCTURIX_D7);
            this.filmsTypes.add(TYPE.STRUCTURIX_D8);
            switch (getType()) {
                case STRUCTURIX_D2:
                    this.setContrast(6.0);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(9.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(7.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(6.4);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(8.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(9.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(9.0);
                    }
                    break;
                case STRUCTURIX_D3_sc:
                    this.setContrast(5.3);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(9.5);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(8.0);
                    } else {
                        this.setRelativeExposureFactors(-1);
                    }
                    break;
                case STRUCTURIX_D3:
                    this.setContrast(5.5);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(4.1);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(4.3);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(3.6);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(5.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(5.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(5.1);
                    }
                    break;
                case STRUCTURIX_D4:
                    this.setContrast(5.4);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(3.0);
                    }

                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(2.7);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(2.4);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(3.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(3.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(3.1);
                    }
                    break;
                case STRUCTURIX_D5:
                    this.setContrast(5.4);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(1.7);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(1.5);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(1.4);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(1.5);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(1.5);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(1.5);
                    }
                    break;
                case STRUCTURIX_D7:
                    this.setContrast(5.4);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(1.0);
                    }
                    break;
                case STRUCTURIX_D8:
                    this.setContrast(4.3);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(6.0);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(0.65);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.SELENIUM_75)) {
                        this.setRelativeExposureFactors(0.6);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(0.6);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(0.6);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.LINAC_8MeV)) {
                        this.setRelativeExposureFactors(0.6);
                    }
                    break;
                default:
                    this.setRelativeExposureFactors(-1);
                    break;
            }


        }
        if (getFilmName().equals(NAME.FUJIFILM)) {
            this.isotopetypes.add(ISOTOPETYPE.X_Ray100KV);
            this.isotopetypes.add(ISOTOPETYPE.X_Ray200KV);
            this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
            this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
            this.filmsTypes.add(TYPE.IX20);
            this.filmsTypes.add(TYPE.IX25);
            this.filmsTypes.add(TYPE.IX50);
            this.filmsTypes.add(TYPE.IX80);
            this.filmsTypes.add(TYPE.IX100);
            this.filmsTypes.add(TYPE.IX150);
            this.filmsTypes.add(TYPE.IX29);
            this.filmsTypes.add(TYPE.IX59);

            switch (getType()) {
                case IX20:
                    String IX20 = "Single emulsion, low-speed," + "\n" +
                            "very-high contrast,ultra-fine graininess film\n" +
                            " Castings : thin part of multi-thickness object\n" +
                            "->Neutron radiography\n" +
                            "-> Micro-electronic parts";
                    this.setFeaturesandMajorApplications(IX20);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(10);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(9);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(8);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(5);
                    }
                    break;
                case IX25:
                    String IX25 = "Low-speed, very-high contrast,\n" +
                            "ultra-fine graininess film\n" +
                            "-> Welds : Very high sensitivity level\n" +
                            "-> Castings : Very high sensitivity level\n" +
                            "-> High-output supervoltage exposure";
                    this.setFeaturesandMajorApplications(IX25);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(20);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(17);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(15);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(10);
                    }
                    break;
                case IX50:
                    String IX50 = "Low-speed, very-high contrast,\n" +
                            "very low graininess film\n" +
                            "-> Welds : High sensitivity level\n" +
                            "-> Castings : High sensitivity level\n" +
                            "-> High energy isotope exposure";
                    this.setFeaturesandMajorApplications(IX50);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(35);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(30);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(30);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(30);
                    }
                    break;
                case IX80:
                    String IX80 = "Low-speed, very-high contrast,\n" +
                            "very low graininess film\n" +
                            "-> Welds : Normal sensitivity level\n" +
                            "-> Castings : Normal sensitivity level\n" +
                            "-> Normaly used for many kind of usage";
                    this.setFeaturesandMajorApplications(IX80);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(55);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(55);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(55);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(55);
                    }
                    break;
                case IX100:
                    String IX100 = "Medium-speed, high contrast,\n" +
                            "low graininess film\n" +
                            "-> Welds : Normal sensitivity level\n" +
                            "-> Castings : Normal sensitivity level\n" +
                            "-> Normaly used for many kind of usage";
                    this.setFeaturesandMajorApplications(IX100);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(100);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(100);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(100);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(100);
                    }
                    break;
                case IX150:
                    String IX150 = "High-speed, medium contrast,\n" +
                            "high graininess film\n" +
                            "-> Heavy metal or thick object inspection\n" +
                            "-> Welds\n" +
                            "-> Castings ";
                    this.setFeaturesandMajorApplications(IX150);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(170);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(170);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(170);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(170);
                    }
                    break;
                case IX29:
                    String IX29 = "Low-speed, high contrast,\n" +
                            "very low graininess film\n" +
                            "-> Castings :\n" +
                            "-> Multi-thickness object";
                    this.setFeaturesandMajorApplications(IX29);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(22);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(22);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(22);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(22);
                    }
                    break;
                case IX59:
                    String IX59 = "Low-speed, high-contrast,\n" +
                            "very low graininess film\n" +
                            "-> Castng :\n" +
                            "-> Multi-thickness object";
                    this.setFeaturesandMajorApplications(IX59);
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray100KV)) {
                        this.setRelativeExposureFactors(45);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.X_Ray200KV)) {
                        this.setRelativeExposureFactors(45);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.IRIDIUM_192)) {
                        this.setRelativeExposureFactors(45);
                    }
                    if (isotopetype.equals(ISOTOPETYPE.COBALT_60)) {
                        this.setRelativeExposureFactors(45);
                    }
                    break;
                default:
                    this.setRelativeExposureFactors(-1);
                    break;

            }

        }
    }

    /**
     * set contrast for KODAK with X_ray 200/220kv
     */
    private void contrastUpdate(int timeS, int temperC, TYPE type) {
        /**
         * Processor Cycle 8 min 79°F or 26°C
         */
        this.setDevelopTemperature_C(temperC);
        this.setDevelopImmersionTime_S(timeS);
        double time = this.getDevelopImmersionTime_S();
        double temp = this.getDevelopTemperature_C();

        switch (type) {
            case DR50:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(5.4);
                    this.setBase_Fog(0.19);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(5.55);
                    this.setBase_Fog(0.20);

                }
                break;
            case M100:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(5.4);
                    this.setBase_Fog(0.19);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(5.25);
                    this.setBase_Fog(0.19);

                }
                break;
            case MX125:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(5.15);
                    this.setBase_Fog(0.20);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(5.05);
                    this.setBase_Fog(0.20);

                }
                break;
            case T200:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(4.7);
                    this.setBase_Fog(0.20);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(4.7);
                    this.setBase_Fog(0.20);

                }
                break;
            case AA400:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(4.7);
                    this.setBase_Fog(0.20);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(4.65);
                    this.setBase_Fog(0.20);

                }
                break;
            case HS800:
                //26°C and 8 min
                if (time == 480 && temp == 26) {
                    this.setContrast(4.4);
                    this.setBase_Fog(0.22);
                }
                //30°C and 5 min
                if (time == 300 && temp == 30) {
                    this.setContrast(4.3);
                    this.setBase_Fog(0.23);

                }


        }
    }

    private void amountFilm() {
        if (getModel().equals(MODEL.ROLL)) {
            switch (getSize()) {
                case ROLLPAC_60m:
                    this.setLengthFilm(60);
                    this.setWidthFilm(-1);
                    break;
                case ROLLPAC_70m:
                    this.setLengthFilm(70);
                    this.setWidthFilm(-1);
                    break;
                case ROLLPAC_70mmX90m:
                    this.setLengthFilm(90);
                    this.setWidthFilm(70);
                    break;
                case ROLLPAC_100mmX90m:
                    this.setLengthFilm(90);
                    this.setWidthFilm(100);
                    break;
                case ROLLPAC_70mmX100m:
                    this.setLengthFilm(100);
                    this.setWidthFilm(70);
                    break;
                case ROLLPAC_100mmX100m:
                    this.setLengthFilm(100);
                    this.setWidthFilm(100);
                    break;
                case ROLLPAC_60mmX150m:
                    this.setLengthFilm(150);
                    this.setWidthFilm(60);
                    break;
                case ROLLPAC_70mmX150m:
                    this.setLengthFilm(150);
                    this.setWidthFilm(70);
                    break;
                case ROLLPAC_100mmX150m:
                    this.setLengthFilm(150);
                    this.setWidthFilm(100);
                    break;
            }

        }
        if (getModel().equals(MODEL.SHEET)) {
            this.setNumberOfBoxSheets(100);
            switch (getSize()) {
                case SHEET_10X20cm:
                    this.setWidthFilm(10);
                    this.setLengthFilm(20);
                    break;
                case SHEET_90X12cm:
                    this.setWidthFilm(90);
                    this.setLengthFilm(12);
                    break;
                case SHEET_35X43cm:
                    this.setWidthFilm(35);
                    this.setLengthFilm(43);
                    break;
                case SHEET_30X40cm:
                    this.setWidthFilm(30);
                    this.setLengthFilm(40);
                    break;
                case SHEET_24X30cm:
                    this.setWidthFilm(24);
                    this.setLengthFilm(30);
                    break;
                case SHEET_18X43cm:
                    this.setWidthFilm(18);
                    this.setLengthFilm(43);
                    break;
                case SHEET_18X24cm:
                    this.setWidthFilm(18);
                    this.setLengthFilm(24);
                    break;
                case SHEET_15X40cm:
                    this.setWidthFilm(15);
                    this.setLengthFilm(40);
                    break;
                case SHEET_13X18cm:
                    this.setWidthFilm(13);
                    this.setLengthFilm(18);
                    break;
                case SHEET_10X48cm:
                    this.setWidthFilm(10);
                    this.setLengthFilm(48);
                    break;
                case SHEET_10X24cm:
                    this.setWidthFilm(10);
                    this.setLengthFilm(24);
                    break;

            }

        }
    }


    public NAME getFilmName() {
        return filmName;
    }

    public void setFilmName(NAME filmName) {
        this.filmName = filmName;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }

    public SIZE getSize() {
        return size;
    }

    public void setSize(SIZE size) {
        this.size = size;
    }

    public double getAmountFilm() {
        return amountFilm;
    }

    public void setAmountFilm(double amountFilm) {
        this.amountFilm = amountFilm;
    }

    public ISOTOPETYPE getIsotopetype() {
        return isotopetype;
    }

    public void setIsotopetype(ISOTOPETYPE isotopetype) {
        this.isotopetype = isotopetype;
    }

    public double getDevelopTemperature_C() {
        return developTemperature_C;
    }

    public void setDevelopTemperature_C(double developTemperature_C) {
        this.developTemperature_C = developTemperature_C;
    }

    public double getDevelopImmersionTime_S() {
        return developImmersionTime_S;
    }

    public void setDevelopImmersionTime_S(double developImmersionTime_S) {
        this.developImmersionTime_S = developImmersionTime_S;
    }

    public double getContrast() {
        return contrast;
    }

    public void setContrast(double contrast) {
        this.contrast = contrast;
    }

    public double getRelativeExposureFactors() {
        return relativeExposureFactors;
    }

    public void setRelativeExposureFactors(double relativeExposureFactors) {
        this.relativeExposureFactors = relativeExposureFactors;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getWeightFilm() {
        return weightFilm;
    }

    public void setWeightFilm(int weightFilm) {
        this.weightFilm = weightFilm;
    }

    public int getLengthFilm() {
        return lengthFilm;
    }

    public void setLengthFilm(int lengthFilm) {
        this.lengthFilm = lengthFilm;
    }

    public int getWidthFilm() {
        return widthFilm;
    }

    public void setWidthFilm(int widthFilm) {
        this.widthFilm = widthFilm;
    }

    public int getNumberOfBoxSheets() {
        return numberOfBoxSheets;
    }

    public void setNumberOfBoxSheets(int numberOfBoxSheets) {
        this.numberOfBoxSheets = numberOfBoxSheets;
    }

    public List<ISOTOPETYPE> getIsotopetypes() {
        return isotopetypes;
    }

    public void setIsotopetypes(ArrayList<ISOTOPETYPE> isotopetypes) {
        this.isotopetypes = isotopetypes;
    }

    /**
     * @param isotopetype
     * @return check if isotope is in list
     */
    public boolean isIsotope(ISOTOPETYPE isotopetype) {
        for (int i = 0; i < isotopetypes.size(); i++) {
            return isotopetypes.get(i).equals(isotopetype);
        }
        return false;
    }

    public List<TYPE> getFilmsTypes() {
        return filmsTypes;
    }

    public void setFilmsTypes(ArrayList<TYPE> filmsTypes) {
        this.filmsTypes = filmsTypes;
    }

    /**
     * @param filmType
     * @return check if FilmType is in list
     */
    public boolean isFilmType(TYPE filmType) {
        for (int i = 0; i < filmsTypes.size(); i++) {
            return filmsTypes.get(i).equals(filmType);
        }
        return false;
    }

    public String getFeaturesandMajorApplications() {
        return featuresandMajorApplications;
    }

    public void setFeaturesandMajorApplications(String featuresandMajorApplications) {
        this.featuresandMajorApplications = featuresandMajorApplications;
    }

    public List<TYPE> getNdtType() {
        return ndtType;
    }

    public void setNdtType(ArrayList<TYPE> ndtType) {
        this.ndtType = ndtType;
    }

    /**
     * @param type
     * @return boolean
     */
    public boolean isNdtType(TYPE type) {
        for (int i = 0; i < ndtType.size(); i++) {
            return ndtType.get(i).equals(type);
        }
        return false;
    }

    public boolean isFilmIsexpirt() {
        return filmIsExpirt;
    }

    public void setFilmIsexpirt(boolean filmIsExpirt) {
        this.filmIsExpirt = filmIsExpirt;
    }

    public boolean isFilmExpirt() {
        Calendar today = Calendar.getInstance();
        if (getExpiryDate() != null && getExpiryDate().equals(today)) {
            setFilmIsexpirt(true);
            return true;
        }
        return false;
    }

    public double getBase_Fog() {
        return base_Fog;
    }

    public void setBase_Fog(double base_Fog) {
        this.base_Fog = base_Fog;
    }


    @Override
    public String toString() {
        return "\n" + "FILM{ " +
                ", FILM_NAME= " + filmName +
                ", SERIAL_NUMBER= " + serialNumber +
                ", ID= " + getId() +
                ", NDT_TYPE= " + ndtType +
                ", FILM_TYPE= " + type +
                ", FILM_MODEL= " + model +
                ", FILM_SIZE= " + size +
                ", LOCATION= " + location +
                ", AMOUNT_FILM= " + amountFilm +
                ", FILM_IS_EXPIRT= " + isFilmExpirt() +
                ", ISOTOPE_TYPES= " + isotopetypes +
                ", FILM_TYPES= " + filmsTypes +
                ", ISOTOPE_TYPE= " + isotopetype +
                ", DEVELOP_TEMPERATURE_°C= " + developTemperature_C +
                ", DEVELOP_IMMERSION_TIME_Sec= " + developImmersionTime_S +
                ", CONTRAST= " + contrast +
                ", RELATIVE_EXPOSURE_FACTORS= " + relativeExposureFactors +
                ", EXPIRY_DATE= " + expiryDate +
                ", NUMBER_OF_BOX_SHEETS= " + numberOfBoxSheets +
                ", WEIGHT_FILM= " + weightFilm +
                ", LENGTH_FILM= " + lengthFilm +
                ", WIDTH_FILM= " + widthFilm +
                ", FEATURES_AND_MAJOR_APPLICATIONS= " + featuresandMajorApplications +
                ", COUNTER = " + getCounter() +
                "}" + "\n";
    }
}
