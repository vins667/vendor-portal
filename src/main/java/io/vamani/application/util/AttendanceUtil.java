package io.vamani.application.util;

import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.model.Attendance;
import io.vamani.application.mssql.domain.DayStatus;
import io.vamani.application.mssql.domain.Hourt;
import io.vamani.application.mssql.domain.Shift;
import io.vamani.application.mssql.domain.Timet;
import io.vamani.application.mssql.repository.ShiftRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class AttendanceUtil {
    private static final String error = "ERROR";
    private static final String leave = "LEAVE";
    private static final String wo = "WO";
    private static final String overtime = "OD";
    private static final String lateIn = "Late IN";
    private static final String earlyOut = "Early Out";
    private static final String sp = "SP";
    private static final String lt = "LT";
    private static final String sl = "SL";
    private static final String singlePunch = "Single Punch";
    private static final String shortLeave = "Short Leave";
    private static final String noPunch = "Absent";
    private static final String wOff = "Weekly Off";
    public static List<Attendance> getAllendance(Timet timet, Shift shift, String month) {
        List<Attendance> attendances = new ArrayList<>();
        try {
            int graceMinute = shift.getGrace();
            String shiftStartTime = shift.getIntime();
            String shiftEndTime = shift.getOuttime();
            SimpleDateFormat minuteDateFormat = new SimpleDateFormat("HH:mm") ;
            Date startMinute = minuteDateFormat.parse(shiftStartTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startMinute);
            cal.add(Calendar.MINUTE, graceMinute);
            shiftStartTime = minuteDateFormat.format(cal.getTime());
            if (timet != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat compSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

                Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                //1 Day
                if ((timet.getMit1() != null && timet.getMit1().length()>0) || (timet.getMot1() != null && timet.getMot1().length()>0)) {
                    Attendance attendance1 = new Attendance();
                    attendance1.setInTime(timet.getMit1());
                    attendance1.setOutTime(timet.getMot1());
                    Instant dt = simpleDateFormat.parse(timet.getInDt1().trim().length() > 0 ? timet.getInDt1().trim() : timet.getOutDt1().trim()).toInstant();
                    attendance1.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit1() != null && timet.getMit1().length()>0) &&  (timet.getMot1() != null && timet.getMot1().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt1().trim() + " " + timet.getMit1());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt1().trim() + " " + timet.getMot1());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt1().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt1().trim() + " " + shiftEndTime);

                            attendance1.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance1.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit1() != null && timet.getMit1().length()>0) ||  (timet.getMot1() != null && timet.getMot1().length()>0)) {
                            attendance1.setFlag(error);
                            attendance1.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance1);
                } else {
                    Date dt = simpleDateFormat.parse("01-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance1 = new Attendance();
                        attendance1.setInTime("");
                        attendance1.setOutTime("");
                        attendance1.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance1.setFlag(error);
                        attendance1.setMsg(noPunch);
                        attendances.add(attendance1);
                    }
                }

                //2 Day
                if ((timet.getMit2() != null && timet.getMit2().length()>0) || (timet.getMot2() != null && timet.getMot2().length()>0)) {
                    Attendance attendance2 = new Attendance();
                    attendance2.setInTime(timet.getMit2());
                    attendance2.setOutTime(timet.getMot2());
                    Instant dt = simpleDateFormat.parse(timet.getInDt2().trim().length() > 0 ? timet.getInDt2().trim() : timet.getOutDt2().trim()).toInstant();
                    attendance2.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit2() != null && timet.getMit2().length()>0) &&  (timet.getMot2() != null && timet.getMot2().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt2().trim() + " " + timet.getMit2());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt2().trim() + " " + timet.getMot2());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt2().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt2().trim() + " " + shiftEndTime);

                            attendance2.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance2.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit2() != null && timet.getMit2().length()>0) ||  (timet.getMot2() != null && timet.getMot2().length()>0)) {
                            attendance2.setFlag(error);
                            attendance2.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance2);
                } else {
                    Date dt = simpleDateFormat.parse("02-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance2 = new Attendance();
                        attendance2.setInTime("");
                        attendance2.setOutTime("");
                        attendance2.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance2.setFlag(error);
                        attendance2.setMsg(noPunch);
                        attendances.add(attendance2);
                    }
                }

                //3 Day
                if ((timet.getMit3() != null && timet.getMit3().length()>0) || (timet.getMot3() != null && timet.getMot3().length()>0)) {
                    Attendance attendance3 = new Attendance();
                    attendance3.setInTime(timet.getMit3());
                    attendance3.setOutTime(timet.getMot3());
                    Instant dt = simpleDateFormat.parse(timet.getInDt3().trim().length() > 0 ? timet.getInDt3().trim() : timet.getOutDt3().trim()).toInstant();
                    attendance3.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit3() != null && timet.getMit3().length()>0) &&  (timet.getMot3() != null && timet.getMot3().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt3().trim() + " " + timet.getMit3());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt3().trim() + " " + timet.getMot3());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt3().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt3().trim() + " " + shiftEndTime);

                            attendance3.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance3.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit3() != null && timet.getMit3().length()>0) ||  (timet.getMot3() != null && timet.getMot3().length()>0)) {
                            attendance3.setFlag(error);
                            attendance3.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance3);
                } else {
                    Date dt = simpleDateFormat.parse("03-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance3 = new Attendance();
                        attendance3.setInTime("");
                        attendance3.setOutTime("");
                        attendance3.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance3.setFlag(error);
                        attendance3.setMsg(noPunch);
                        attendances.add(attendance3);
                    }
                }

                //4 Day
                if ((timet.getMit4() != null && timet.getMit4().length()>0) || (timet.getMot4() != null && timet.getMot4().length()>0)) {
                    Attendance attendance4 = new Attendance();
                    attendance4.setInTime(timet.getMit4());
                    attendance4.setOutTime(timet.getMot4());
                    Instant dt = simpleDateFormat.parse(timet.getInDt4().trim().length() > 0 ? timet.getInDt4().trim() : timet.getOutDt4().trim()).toInstant();
                    attendance4.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit4() != null && timet.getMit4().length()>0) &&  (timet.getMot4() != null && timet.getMot4().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt4().trim() + " " + timet.getMit4());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt4().trim() + " " + timet.getMot4());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt4().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt4().trim() + " " + shiftEndTime);

                            attendance4.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance4.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit4() != null && timet.getMit4().length()>0) ||  (timet.getMot4() != null && timet.getMot4().length()>0)) {
                            attendance4.setFlag(error);
                            attendance4.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance4);
                } else {
                    Date dt = simpleDateFormat.parse("04-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance4 = new Attendance();
                        attendance4.setInTime("");
                        attendance4.setOutTime("");
                        attendance4.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance4.setFlag(error);
                        attendance4.setMsg(noPunch);
                        attendances.add(attendance4);
                    }
                }

                //5 Day
                if ((timet.getMit5() != null && timet.getMit5().length()>0) || (timet.getMot5() != null && timet.getMot5().length()>0)) {
                    Attendance attendance5 = new Attendance();
                    attendance5.setInTime(timet.getMit5());
                    attendance5.setOutTime(timet.getMot5());
                    Instant dt = simpleDateFormat.parse(timet.getInDt5().trim().length() > 0 ? timet.getInDt5().trim() : timet.getOutDt5().trim()).toInstant();
                    attendance5.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit5() != null && timet.getMit5().length()>0) &&  (timet.getMot5() != null && timet.getMot5().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt5().trim() + " " + timet.getMit5());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt5().trim() + " " + timet.getMot5());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt5().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt5().trim() + " " + shiftEndTime);

                            attendance5.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance5.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit5() != null && timet.getMit5().length()>0) ||  (timet.getMot5() != null && timet.getMot5().length()>0)) {
                            attendance5.setFlag(error);
                            attendance5.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance5);
                } else {
                    Date dt = simpleDateFormat.parse("05-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance5 = new Attendance();
                        attendance5.setInTime("");
                        attendance5.setOutTime("");
                        attendance5.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance5.setFlag(error);
                        attendance5.setMsg(noPunch);
                        attendances.add(attendance5);
                    }
                }

                //6 Day
                if ((timet.getMit6() != null && timet.getMit6().length()>0) || (timet.getMot6() != null && timet.getMot6().length()>0)) {
                    Attendance attendance6 = new Attendance();
                    attendance6.setInTime(timet.getMit6());
                    attendance6.setOutTime(timet.getMot6());
                    Instant dt = simpleDateFormat.parse(timet.getInDt6().trim().length() > 0 ? timet.getInDt6().trim() : timet.getOutDt6().trim()).toInstant();
                    attendance6.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit6() != null && timet.getMit6().length()>0) &&  (timet.getMot6() != null && timet.getMot6().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt6().trim() + " " + timet.getMit6());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt6().trim() + " " + timet.getMot6());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt6().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt6().trim() + " " + shiftEndTime);

                            attendance6.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance6.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit6() != null && timet.getMit6().length()>0) ||  (timet.getMot6() != null && timet.getMot6().length()>0)) {
                            attendance6.setFlag(error);
                            attendance6.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance6);
                } else {
                    Date dt = simpleDateFormat.parse("06-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance6 = new Attendance();
                        attendance6.setInTime("");
                        attendance6.setOutTime("");
                        attendance6.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance6.setFlag(error);
                        attendance6.setMsg(noPunch);
                        attendances.add(attendance6);
                    }
                }

                //7 Day
                if ((timet.getMit7() != null && timet.getMit7().length()>0) || (timet.getMot7() != null && timet.getMot7().length()>0)) {
                    Attendance attendance7 = new Attendance();
                    attendance7.setInTime(timet.getMit7());
                    attendance7.setOutTime(timet.getMot7());
                    Instant dt = simpleDateFormat.parse(timet.getInDt7().trim().length() > 0 ? timet.getInDt7().trim() : timet.getOutDt7().trim()).toInstant();
                    attendance7.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit7() != null && timet.getMit7().length()>0) &&  (timet.getMot7() != null && timet.getMot7().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt7().trim() + " " + timet.getMit7());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt7().trim() + " " + timet.getMot7());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt7().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt7().trim() + " " + shiftEndTime);

                            attendance7.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance7.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit7() != null && timet.getMit7().length()>0) ||  (timet.getMot7() != null && timet.getMot7().length()>0)) {
                            attendance7.setFlag(error);
                            attendance7.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance7);
                } else {
                    Date dt = simpleDateFormat.parse("07-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance7 = new Attendance();
                        attendance7.setInTime("");
                        attendance7.setOutTime("");
                        attendance7.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance7.setFlag(error);
                        attendance7.setMsg(noPunch);
                        attendances.add(attendance7);
                    }
                }

                //8 Day
                if ((timet.getMit8() != null && timet.getMit8().length()>0) || (timet.getMot8() != null && timet.getMot8().length()>0)) {
                    Attendance attendance8 = new Attendance();
                    attendance8.setInTime(timet.getMit8());
                    attendance8.setOutTime(timet.getMot8());
                    Instant dt = simpleDateFormat.parse(timet.getInDt8().trim().length() > 0 ? timet.getInDt8().trim() : timet.getOutDt8().trim()).toInstant();
                    attendance8.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit8() != null && timet.getMit8().length()>0) &&  (timet.getMot8() != null && timet.getMot8().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt8().trim() + " " + timet.getMit8());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt8().trim() + " " + timet.getMot8());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt8().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt8().trim() + " " + shiftEndTime);

                            attendance8.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance8.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit8() != null && timet.getMit8().length()>0) ||  (timet.getMot8() != null && timet.getMot8().length()>0)) {
                            attendance8.setFlag(error);
                            attendance8.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance8);
                } else {
                    Date dt = simpleDateFormat.parse("08-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance8 = new Attendance();
                        attendance8.setInTime("");
                        attendance8.setOutTime("");
                        attendance8.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance8.setFlag(error);
                        attendance8.setMsg(noPunch);
                        attendances.add(attendance8);
                    }
                }

                //9 Day
                if ((timet.getMit9() != null && timet.getMit9().length()>0) || (timet.getMot9() != null && timet.getMot9().length()>0)) {
                    Attendance attendance9 = new Attendance();
                    attendance9.setInTime(timet.getMit9());
                    attendance9.setOutTime(timet.getMot9());
                    Instant dt = simpleDateFormat.parse(timet.getInDt9().trim().length() > 0 ? timet.getInDt9().trim() : timet.getOutDt9().trim()).toInstant();
                    attendance9.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit9() != null && timet.getMit9().length()>0) &&  (timet.getMot9() != null && timet.getMot9().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt9().trim() + " " + timet.getMit9());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt9().trim() + " " + timet.getMot9());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt9().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt9().trim() + " " + shiftEndTime);

                            attendance9.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance9.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit9() != null && timet.getMit9().length()>0) ||  (timet.getMot9() != null && timet.getMot9().length()>0)) {
                            attendance9.setFlag(error);
                            attendance9.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance9);
                } else {
                    Date dt = simpleDateFormat.parse("09-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance9 = new Attendance();
                        attendance9.setInTime("");
                        attendance9.setOutTime("");
                        attendance9.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance9.setFlag(error);
                        attendance9.setMsg(noPunch);
                        attendances.add(attendance9);
                    }
                }

                //10 Day
                if ((timet.getMit10() != null && timet.getMit10().length()>0) || (timet.getMot10() != null && timet.getMot10().length()>0)) {
                    Attendance attendance10 = new Attendance();
                    attendance10.setInTime(timet.getMit10());
                    attendance10.setOutTime(timet.getMot10());
                    Instant dt = simpleDateFormat.parse(timet.getInDt10().trim().length() > 0 ? timet.getInDt10().trim() : timet.getOutDt10().trim()).toInstant();
                    attendance10.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit10() != null && timet.getMit10().length()>0) &&  (timet.getMot10() != null && timet.getMot10().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt10().trim() + " " + timet.getMit10());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt10().trim() + " " + timet.getMot10());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt10().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt10().trim() + " " + shiftEndTime);

                            attendance10.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance10.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit10() != null && timet.getMit10().length()>0) ||  (timet.getMot10() != null && timet.getMot10().length()>0)) {
                            attendance10.setFlag(error);
                            attendance10.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance10);
                } else {
                    Date dt = simpleDateFormat.parse("10-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance10 = new Attendance();
                        attendance10.setInTime("");
                        attendance10.setOutTime("");
                        attendance10.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance10.setFlag(error);
                        attendance10.setMsg(noPunch);
                        attendances.add(attendance10);
                    }
                }

                //11 Day
                if ((timet.getMit11() != null && timet.getMit11().length()>0) || (timet.getMot11() != null && timet.getMot11().length()>0)) {
                    Attendance attendance11 = new Attendance();
                    attendance11.setInTime(timet.getMit11());
                    attendance11.setOutTime(timet.getMot11());
                    Instant dt = simpleDateFormat.parse(timet.getInDt11().trim().length() > 0 ? timet.getInDt11().trim() : timet.getOutDt11().trim()).toInstant();
                    attendance11.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit11() != null && timet.getMit11().length()>0) &&  (timet.getMot11() != null && timet.getMot11().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt11().trim() + " " + timet.getMit11());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt11().trim() + " " + timet.getMot11());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt11().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt11().trim() + " " + shiftEndTime);

                            attendance11.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance11.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit11() != null && timet.getMit11().length()>0) ||  (timet.getMot11() != null && timet.getMot11().length()>0)) {
                            attendance11.setFlag(error);
                            attendance11.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance11);
                } else {
                    Date dt = simpleDateFormat.parse("11-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance11 = new Attendance();
                        attendance11.setInTime("");
                        attendance11.setOutTime("");
                        attendance11.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance11.setFlag(error);
                        attendance11.setMsg(noPunch);
                        attendances.add(attendance11);
                    }
                }

                //12 Day
                if ((timet.getMit12() != null && timet.getMit12().length()>0) || (timet.getMot12() != null && timet.getMot12().length()>0)) {
                    Attendance attendance12 = new Attendance();
                    attendance12.setInTime(timet.getMit12());
                    attendance12.setOutTime(timet.getMot12());
                    Instant dt = simpleDateFormat.parse(timet.getInDt12().trim().length() > 0 ? timet.getInDt12().trim() : timet.getOutDt12().trim()).toInstant();
                    attendance12.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit12() != null && timet.getMit12().length()>0) &&  (timet.getMot12() != null && timet.getMot12().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt12().trim() + " " + timet.getMit12());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt12().trim() + " " + timet.getMot12());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt12().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt12().trim() + " " + shiftEndTime);

                            attendance12.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance12.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit12() != null && timet.getMit12().length()>0) ||  (timet.getMot12() != null && timet.getMot12().length()>0)) {
                            attendance12.setFlag(error);
                            attendance12.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance12);
                } else {
                    Date dt = simpleDateFormat.parse("12-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance12 = new Attendance();
                        attendance12.setInTime("");
                        attendance12.setOutTime("");
                        attendance12.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance12.setFlag(error);
                        attendance12.setMsg(noPunch);
                        attendances.add(attendance12);
                    }
                }

                //13 Day
                if ((timet.getMit13() != null && timet.getMit13().length()>0) || (timet.getMot13() != null && timet.getMot13().length()>0)) {
                    Attendance attendance13 = new Attendance();
                    attendance13.setInTime(timet.getMit13());
                    attendance13.setOutTime(timet.getMot13());
                    Instant dt = simpleDateFormat.parse(timet.getInDt13().trim().length() > 0 ? timet.getInDt13().trim() : timet.getOutDt13().trim()).toInstant();
                    attendance13.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit13() != null && timet.getMit13().length()>0) &&  (timet.getMot13() != null && timet.getMot13().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt13().trim() + " " + timet.getMit13());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt13().trim() + " " + timet.getMot13());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt13().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt13().trim() + " " + shiftEndTime);

                            attendance13.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance13.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit13() != null && timet.getMit13().length()>0) ||  (timet.getMot13() != null && timet.getMot13().length()>0)) {
                            attendance13.setFlag(error);
                            attendance13.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance13);
                } else {
                    Date dt = simpleDateFormat.parse("13-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance13 = new Attendance();
                        attendance13.setInTime("");
                        attendance13.setOutTime("");
                        attendance13.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance13.setFlag(error);
                        attendance13.setMsg(noPunch);
                        attendances.add(attendance13);
                    }
                }

                //14 Day
                if ((timet.getMit14() != null && timet.getMit14().length()>0) || (timet.getMot14() != null && timet.getMot14().length()>0)) {
                    Attendance attendance14 = new Attendance();
                    attendance14.setInTime(timet.getMit14());
                    attendance14.setOutTime(timet.getMot14());
                    Instant dt = simpleDateFormat.parse(timet.getInDt14().trim().length() > 0 ? timet.getInDt14().trim() : timet.getOutDt14().trim()).toInstant();
                    attendance14.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit14() != null && timet.getMit14().length()>0) &&  (timet.getMot14() != null && timet.getMot14().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt14().trim() + " " + timet.getMit14());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt14().trim() + " " + timet.getMot14());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt14().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt14().trim() + " " + shiftEndTime);

                            attendance14.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance14.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit14() != null && timet.getMit14().length()>0) ||  (timet.getMot14() != null && timet.getMot14().length()>0)) {
                            attendance14.setFlag(error);
                            attendance14.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance14);
                } else {
                    Date dt = simpleDateFormat.parse("14-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance14 = new Attendance();
                        attendance14.setInTime("");
                        attendance14.setOutTime("");
                        attendance14.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance14.setFlag(error);
                        attendance14.setMsg(noPunch);
                        attendances.add(attendance14);
                    }
                }

                //15 Day
                if ((timet.getMit15() != null && timet.getMit15().length()>0) || (timet.getMot15() != null && timet.getMot15().length()>0)) {
                    Attendance attendance15 = new Attendance();
                    attendance15.setInTime(timet.getMit15());
                    attendance15.setOutTime(timet.getMot15());
                    Instant dt = simpleDateFormat.parse(timet.getInDt15().trim().length() > 0 ? timet.getInDt15().trim() : timet.getOutDt15().trim()).toInstant();
                    attendance15.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit15() != null && timet.getMit15().length()>0) &&  (timet.getMot15() != null && timet.getMot15().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt15().trim() + " " + timet.getMit15());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt15().trim() + " " + timet.getMot15());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt15().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt15().trim() + " " + shiftEndTime);

                            attendance15.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance15.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit15() != null && timet.getMit15().length()>0) ||  (timet.getMot15() != null && timet.getMot15().length()>0)) {
                            attendance15.setFlag(error);
                            attendance15.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance15);
                } else {
                    Date dt = simpleDateFormat.parse("15-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance15 = new Attendance();
                        attendance15.setInTime("");
                        attendance15.setOutTime("");
                        attendance15.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance15.setFlag(error);
                        attendance15.setMsg(noPunch);
                        attendances.add(attendance15);
                    }
                }

                //16 Day
                if ((timet.getMit16() != null && timet.getMit16().length()>0) || (timet.getMot16() != null && timet.getMot16().length()>0)) {
                    Attendance attendance16 = new Attendance();
                    attendance16.setInTime(timet.getMit16());
                    attendance16.setOutTime(timet.getMot16());
                    Instant dt = simpleDateFormat.parse(timet.getInDt16().trim().length() > 0 ? timet.getInDt16().trim() : timet.getOutDt16().trim()).toInstant();
                    attendance16.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit16() != null && timet.getMit16().length()>0) &&  (timet.getMot16() != null && timet.getMot16().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt16().trim() + " " + timet.getMit16());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt16().trim() + " " + timet.getMot16());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt16().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt16().trim() + " " + shiftEndTime);

                            attendance16.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance16.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit16() != null && timet.getMit16().length()>0) ||  (timet.getMot16() != null && timet.getMot16().length()>0)) {
                            attendance16.setFlag(error);
                            attendance16.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance16);
                } else {
                    Date dt = simpleDateFormat.parse("16-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance16 = new Attendance();
                        attendance16.setInTime("");
                        attendance16.setOutTime("");
                        attendance16.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance16.setFlag(error);
                        attendance16.setMsg(noPunch);
                        attendances.add(attendance16);
                    }
                }

                //17 Day
                if ((timet.getMit17() != null && timet.getMit17().length()>0) || (timet.getMot17() != null && timet.getMot17().length()>0)) {
                    Attendance attendance17 = new Attendance();
                    attendance17.setInTime(timet.getMit17());
                    attendance17.setOutTime(timet.getMot17());
                    Instant dt = simpleDateFormat.parse(timet.getInDt17().trim().length() > 0 ? timet.getInDt17().trim() : timet.getOutDt17().trim()).toInstant();
                    attendance17.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit17() != null && timet.getMit17().length()>0) &&  (timet.getMot17() != null && timet.getMot17().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt17().trim() + " " + timet.getMit17());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt17().trim() + " " + timet.getMot17());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt17().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt17().trim() + " " + shiftEndTime);

                            attendance17.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance17.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit17() != null && timet.getMit17().length()>0) ||  (timet.getMot17() != null && timet.getMot17().length()>0)) {
                            attendance17.setFlag(error);
                            attendance17.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance17);
                } else {
                    Date dt = simpleDateFormat.parse("17-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance17 = new Attendance();
                        attendance17.setInTime("");
                        attendance17.setOutTime("");
                        attendance17.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance17.setFlag(error);
                        attendance17.setMsg(noPunch);
                        attendances.add(attendance17);
                    }
                }

                //18 Day
                if ((timet.getMit18() != null && timet.getMit18().length()>0) || (timet.getMot18() != null && timet.getMot18().length()>0)) {
                    Attendance attendance18 = new Attendance();
                    attendance18.setInTime(timet.getMit18());
                    attendance18.setOutTime(timet.getMot18());
                    Instant dt = simpleDateFormat.parse(timet.getInDt18().trim().length() > 0 ? timet.getInDt18().trim() : timet.getOutDt18().trim()).toInstant();
                    attendance18.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit18() != null && timet.getMit18().length()>0) &&  (timet.getMot18() != null && timet.getMot18().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt18().trim() + " " + timet.getMit18());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt18().trim() + " " + timet.getMot18());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt18().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt18().trim() + " " + shiftEndTime);

                            attendance18.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance18.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit18() != null && timet.getMit18().length()>0) ||  (timet.getMot18() != null && timet.getMot18().length()>0)) {
                            attendance18.setFlag(error);
                            attendance18.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance18);
                } else {
                    Date dt = simpleDateFormat.parse("18-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance18 = new Attendance();
                        attendance18.setInTime("");
                        attendance18.setOutTime("");
                        attendance18.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance18.setFlag(error);
                        attendance18.setMsg(noPunch);
                        attendances.add(attendance18);
                    }
                }

                //19 Day
                if ((timet.getMit19() != null && timet.getMit19().length()>0) || (timet.getMot19() != null && timet.getMot19().length()>0)) {
                    Attendance attendance19 = new Attendance();
                    attendance19.setInTime(timet.getMit19());
                    attendance19.setOutTime(timet.getMot19());
                    Instant dt = simpleDateFormat.parse(timet.getInDt19().trim().length() > 0 ? timet.getInDt19().trim() : timet.getOutDt19().trim()).toInstant();
                    attendance19.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit19() != null && timet.getMit19().length()>0) &&  (timet.getMot19() != null && timet.getMot19().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt19().trim() + " " + timet.getMit19());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt19().trim() + " " + timet.getMot19());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt19().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt19().trim() + " " + shiftEndTime);

                            attendance19.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance19.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit19() != null && timet.getMit19().length()>0) ||  (timet.getMot19() != null && timet.getMot19().length()>0)) {
                            attendance19.setFlag(error);
                            attendance19.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance19);
                } else {
                    Date dt = simpleDateFormat.parse("19-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance19 = new Attendance();
                        attendance19.setInTime("");
                        attendance19.setOutTime("");
                        attendance19.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance19.setFlag(error);
                        attendance19.setMsg(noPunch);
                        attendances.add(attendance19);
                    }
                }

                //20 Day
                if ((timet.getMit20() != null && timet.getMit20().length()>0) || (timet.getMot20() != null && timet.getMot20().length()>0)) {
                    Attendance attendance20 = new Attendance();
                    attendance20.setInTime(timet.getMit20());
                    attendance20.setOutTime(timet.getMot20());
                    Instant dt = simpleDateFormat.parse(timet.getInDt20().trim().length() > 0 ? timet.getInDt20().trim() : timet.getOutDt20().trim()).toInstant();
                    attendance20.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit20() != null && timet.getMit20().length()>0) &&  (timet.getMot20() != null && timet.getMot20().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt20().trim() + " " + timet.getMit20());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt20().trim() + " " + timet.getMot20());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt20().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt20().trim() + " " + shiftEndTime);

                            attendance20.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance20.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit20() != null && timet.getMit20().length()>0) ||  (timet.getMot20() != null && timet.getMot20().length()>0)) {
                            attendance20.setFlag(error);
                            attendance20.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance20);
                } else {
                    Date dt = simpleDateFormat.parse("20-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance20 = new Attendance();
                        attendance20.setInTime("");
                        attendance20.setOutTime("");
                        attendance20.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance20.setFlag(error);
                        attendance20.setMsg(noPunch);
                        attendances.add(attendance20);
                    }
                }

                //21 Day
                if ((timet.getMit21() != null && timet.getMit21().length()>0) || (timet.getMot21() != null && timet.getMot21().length()>0)) {
                    Attendance attendance21 = new Attendance();
                    attendance21.setInTime(timet.getMit21());
                    attendance21.setOutTime(timet.getMot21());
                    Instant dt = simpleDateFormat.parse(timet.getInDt21().trim().length() > 0 ? timet.getInDt21().trim() : timet.getOutDt21().trim()).toInstant();
                    attendance21.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit21() != null && timet.getMit21().length()>0) &&  (timet.getMot21() != null && timet.getMot21().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt21().trim() + " " + timet.getMit21());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt21().trim() + " " + timet.getMot21());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt21().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt21().trim() + " " + shiftEndTime);

                            attendance21.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance21.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit21() != null && timet.getMit21().length()>0) ||  (timet.getMot21() != null && timet.getMot21().length()>0)) {
                            attendance21.setFlag(error);
                            attendance21.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance21);
                } else {
                    Date dt = simpleDateFormat.parse("21-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance21 = new Attendance();
                        attendance21.setInTime("");
                        attendance21.setOutTime("");
                        attendance21.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance21.setFlag(error);
                        attendance21.setMsg(noPunch);
                        attendances.add(attendance21);
                    }
                }

                //22 Day
                if ((timet.getMit22() != null && timet.getMit22().length()>0) || (timet.getMot22() != null && timet.getMot22().length()>0)) {
                    Attendance attendance22 = new Attendance();
                    attendance22.setInTime(timet.getMit22());
                    attendance22.setOutTime(timet.getMot22());
                    Instant dt = simpleDateFormat.parse(timet.getInDt22().trim().length() > 0 ? timet.getInDt22().trim() : timet.getOutDt22().trim()).toInstant();
                    attendance22.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit22() != null && timet.getMit22().length()>0) &&  (timet.getMot22() != null && timet.getMot22().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt22().trim() + " " + timet.getMit22());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt22().trim() + " " + timet.getMot22());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt22().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt22().trim() + " " + shiftEndTime);

                            attendance22.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance22.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit22() != null && timet.getMit22().length()>0) ||  (timet.getMot22() != null && timet.getMot22().length()>0)) {
                            attendance22.setFlag(error);
                            attendance22.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance22);
                } else {
                    Date dt = simpleDateFormat.parse("22-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance22 = new Attendance();
                        attendance22.setInTime("");
                        attendance22.setOutTime("");
                        attendance22.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance22.setFlag(error);
                        attendance22.setMsg(noPunch);
                        attendances.add(attendance22);
                    }
                }

                //23 Day
                if ((timet.getMit23() != null && timet.getMit23().length()>0) || (timet.getMot23() != null && timet.getMot23().length()>0)) {
                    Attendance attendance23 = new Attendance();
                    attendance23.setInTime(timet.getMit23());
                    attendance23.setOutTime(timet.getMot23());
                    Instant dt = simpleDateFormat.parse(timet.getInDt23().trim().length() > 0 ? timet.getInDt23().trim() : timet.getOutDt23().trim()).toInstant();
                    attendance23.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit23() != null && timet.getMit23().length()>0) &&  (timet.getMot23() != null && timet.getMot23().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt23().trim() + " " + timet.getMit23());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt23().trim() + " " + timet.getMot23());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt23().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt23().trim() + " " + shiftEndTime);

                            attendance23.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance23.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit23() != null && timet.getMit23().length()>0) ||  (timet.getMot23() != null && timet.getMot23().length()>0)) {
                            attendance23.setFlag(error);
                            attendance23.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance23);
                } else {
                    Date dt = simpleDateFormat.parse("23-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance23 = new Attendance();
                        attendance23.setInTime("");
                        attendance23.setOutTime("");
                        attendance23.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance23.setFlag(error);
                        attendance23.setMsg(noPunch);
                        attendances.add(attendance23);
                    }
                }

                //24 Day
                if ((timet.getMit24() != null && timet.getMit24().length()>0) || (timet.getMot24() != null && timet.getMot24().length()>0)) {
                    Attendance attendance24 = new Attendance();
                    attendance24.setInTime(timet.getMit24());
                    attendance24.setOutTime(timet.getMot24());
                    Instant dt = simpleDateFormat.parse(timet.getInDt24().trim().length() > 0 ? timet.getInDt24().trim() : timet.getOutDt24().trim()).toInstant();
                    attendance24.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit24() != null && timet.getMit24().length()>0) &&  (timet.getMot24() != null && timet.getMot24().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt24().trim() + " " + timet.getMit24());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt24().trim() + " " + timet.getMot24());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt24().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt24().trim() + " " + shiftEndTime);

                            attendance24.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance24.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit24() != null && timet.getMit24().length()>0) ||  (timet.getMot24() != null && timet.getMot24().length()>0)) {
                            attendance24.setFlag(error);
                            attendance24.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance24);
                } else {
                    Date dt = simpleDateFormat.parse("24-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance24 = new Attendance();
                        attendance24.setInTime("");
                        attendance24.setOutTime("");
                        attendance24.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance24.setFlag(error);
                        attendance24.setMsg(noPunch);
                        attendances.add(attendance24);
                    }
                }

                //25 Day
                if ((timet.getMit25() != null && timet.getMit25().length()>0) || (timet.getMot25() != null && timet.getMot25().length()>0)) {
                    Attendance attendance25 = new Attendance();
                    attendance25.setInTime(timet.getMit25());
                    attendance25.setOutTime(timet.getMot25());
                    Instant dt = simpleDateFormat.parse(timet.getInDt25().trim().length() > 0 ? timet.getInDt25().trim() : timet.getOutDt25().trim()).toInstant();
                    attendance25.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit25() != null && timet.getMit25().length()>0) &&  (timet.getMot25() != null && timet.getMot25().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt25().trim() + " " + timet.getMit25());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt25().trim() + " " + timet.getMot25());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt25().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt25().trim() + " " + shiftEndTime);

                            attendance25.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance25.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit25() != null && timet.getMit25().length()>0) ||  (timet.getMot25() != null && timet.getMot25().length()>0)) {
                            attendance25.setFlag(error);
                            attendance25.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance25);
                } else {
                    Date dt = simpleDateFormat.parse("25-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance25 = new Attendance();
                        attendance25.setInTime("");
                        attendance25.setOutTime("");
                        attendance25.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance25.setFlag(error);
                        attendance25.setMsg(noPunch);
                        attendances.add(attendance25);
                    }
                }

                //26 Day
                if ((timet.getMit26() != null && timet.getMit26().length()>0) || (timet.getMot26() != null && timet.getMot26().length()>0)) {
                    Attendance attendance26 = new Attendance();
                    attendance26.setInTime(timet.getMit26());
                    attendance26.setOutTime(timet.getMot26());
                    Instant dt = simpleDateFormat.parse(timet.getInDt26().trim().length() > 0 ? timet.getInDt26().trim() : timet.getOutDt26().trim()).toInstant();
                    attendance26.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit26() != null && timet.getMit26().length()>0) &&  (timet.getMot26() != null && timet.getMot26().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt26().trim() + " " + timet.getMit26());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt26().trim() + " " + timet.getMot26());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt26().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt26().trim() + " " + shiftEndTime);

                            attendance26.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance26.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit26() != null && timet.getMit26().length()>0) ||  (timet.getMot26() != null && timet.getMot26().length()>0)) {
                            attendance26.setFlag(error);
                            attendance26.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance26);
                } else {
                    Date dt = simpleDateFormat.parse("26-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance26 = new Attendance();
                        attendance26.setInTime("");
                        attendance26.setOutTime("");
                        attendance26.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance26.setFlag(error);
                        attendance26.setMsg(noPunch);
                        attendances.add(attendance26);
                    }
                }

                //27 Day
                if ((timet.getMit27() != null && timet.getMit27().length()>0) || (timet.getMot27() != null && timet.getMot27().length()>0)) {
                    Attendance attendance27 = new Attendance();
                    attendance27.setInTime(timet.getMit27());
                    attendance27.setOutTime(timet.getMot27());
                    Instant dt = simpleDateFormat.parse(timet.getInDt27().trim().length() > 0 ? timet.getInDt27().trim() : timet.getOutDt27().trim()).toInstant();
                    attendance27.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit27() != null && timet.getMit27().length()>0) &&  (timet.getMot27() != null && timet.getMot27().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt27().trim() + " " + timet.getMit27());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt27().trim() + " " + timet.getMot27());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt27().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt27().trim() + " " + shiftEndTime);

                            attendance27.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance27.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit27() != null && timet.getMit27().length()>0) ||  (timet.getMot27() != null && timet.getMot27().length()>0)) {
                            attendance27.setFlag(error);
                            attendance27.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance27);
                } else {
                    Date dt = simpleDateFormat.parse("27-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance27 = new Attendance();
                        attendance27.setInTime("");
                        attendance27.setOutTime("");
                        attendance27.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance27.setFlag(error);
                        attendance27.setMsg(noPunch);
                        attendances.add(attendance27);
                    }
                }

                //28 Day
                if ((timet.getMit28() != null && timet.getMit28().length()>0) || (timet.getMot28() != null && timet.getMot28().length()>0)) {
                    Attendance attendance28 = new Attendance();
                    attendance28.setInTime(timet.getMit28());
                    attendance28.setOutTime(timet.getMot28());
                    Instant dt = simpleDateFormat.parse(timet.getInDt28().trim().length() > 0 ? timet.getInDt28().trim() : timet.getOutDt28().trim()).toInstant();
                    attendance28.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit28() != null && timet.getMit28().length()>0) &&  (timet.getMot28() != null && timet.getMot28().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt28().trim() + " " + timet.getMit28());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt28().trim() + " " + timet.getMot28());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt28().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt28().trim() + " " + shiftEndTime);

                            attendance28.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance28.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit28() != null && timet.getMit28().length()>0) ||  (timet.getMot28() != null && timet.getMot28().length()>0)) {
                            attendance28.setFlag(error);
                            attendance28.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance28);
                } else {
                    Date dt = simpleDateFormat.parse("28-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance28 = new Attendance();
                        attendance28.setInTime("");
                        attendance28.setOutTime("");
                        attendance28.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance28.setFlag(error);
                        attendance28.setMsg(noPunch);
                        attendances.add(attendance28);
                    }
                }

                //29 Day
                if ((timet.getMit29() != null && timet.getMit29().length()>0) || (timet.getMot29() != null && timet.getMot29().length()>0)) {
                    Attendance attendance29 = new Attendance();
                    attendance29.setInTime(timet.getMit29());
                    attendance29.setOutTime(timet.getMot29());
                    Instant dt = simpleDateFormat.parse(timet.getInDt29().trim().length() > 0 ? timet.getInDt29().trim() : timet.getOutDt29().trim()).toInstant();
                    attendance29.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit29() != null && timet.getMit29().length()>0) &&  (timet.getMot29() != null && timet.getMot29().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt29().trim() + " " + timet.getMit29());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt29().trim() + " " + timet.getMot29());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt29().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt29().trim() + " " + shiftEndTime);

                            attendance29.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance29.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit29() != null && timet.getMit29().length()>0) ||  (timet.getMot29() != null && timet.getMot29().length()>0)) {
                            attendance29.setFlag(error);
                            attendance29.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance29);
                } else {
                    Date dt = simpleDateFormat.parse("29-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance29 = new Attendance();
                        attendance29.setInTime("");
                        attendance29.setOutTime("");
                        attendance29.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance29.setFlag(error);
                        attendance29.setMsg(noPunch);
                        attendances.add(attendance29);
                    }
                }

                //30 Day
                if ((timet.getMit30() != null && timet.getMit30().length()>0) || (timet.getMot30() != null && timet.getMot30().length()>0)) {
                    Attendance attendance30 = new Attendance();
                    attendance30.setInTime(timet.getMit30());
                    attendance30.setOutTime(timet.getMot30());
                    Instant dt = simpleDateFormat.parse(timet.getInDt30().trim().length() > 0 ? timet.getInDt30().trim() : timet.getOutDt30().trim()).toInstant();
                    attendance30.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit30() != null && timet.getMit30().length()>0) &&  (timet.getMot30() != null && timet.getMot30().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt30().trim() + " " + timet.getMit30());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt30().trim() + " " + timet.getMot30());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt30().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt30().trim() + " " + shiftEndTime);

                            attendance30.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance30.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit30() != null && timet.getMit30().length()>0) ||  (timet.getMot30() != null && timet.getMot30().length()>0)) {
                            attendance30.setFlag(error);
                            attendance30.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance30);
                } else {
                    Date dt = simpleDateFormat.parse("30-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance30 = new Attendance();
                        attendance30.setInTime("");
                        attendance30.setOutTime("");
                        attendance30.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance30.setFlag(error);
                        attendance30.setMsg(noPunch);
                        attendances.add(attendance30);
                    }
                }

                //31 Day
                if ((timet.getMit31() != null && timet.getMit31().length()>0) || (timet.getMot31() != null && timet.getMot31().length()>0)) {
                    Attendance attendance31 = new Attendance();
                    attendance31.setInTime(timet.getMit31());
                    attendance31.setOutTime(timet.getMot31());
                    Instant dt = simpleDateFormat.parse(timet.getInDt31().trim().length() > 0 ? timet.getInDt31().trim() : timet.getOutDt31().trim()).toInstant();
                    attendance31.setAttendanceDate(dt.atZone(ZoneId.systemDefault()).toLocalDate());

                    if(date.getTime() == Date.from(dt).getTime()) {} else {
                        if((timet.getMit31() != null && timet.getMit31().length()>0) &&  (timet.getMot31() != null && timet.getMot31().length()>0)) {
                            //Logic
                            Date inTime = compSimpleDateFormat.parse(timet.getInDt31().trim() + " " + timet.getMit31());
                            Date outTime = compSimpleDateFormat.parse(timet.getInDt31().trim() + " " + timet.getMot31());

                            Date validInTime = compSimpleDateFormat.parse(timet.getInDt31().trim() + " " + shiftStartTime);
                            Date validOutTime = compSimpleDateFormat.parse(timet.getInDt31().trim() + " " + shiftEndTime);

                            attendance31.setFlag(inTime.after(validInTime) ? error : outTime.before(validOutTime) ? error : null);
                            attendance31.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                        } else if((timet.getMit31() != null && timet.getMit31().length()>0) ||  (timet.getMot31() != null && timet.getMot31().length()>0)) {
                            attendance31.setFlag(error);
                            attendance31.setMsg(singlePunch);
                        }
                    }
                    attendances.add(attendance31);
                } else {
                    Date dt = simpleDateFormat.parse("31-" + month);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if(date.before(dt)) {} else if(date.getTime() == dt.getTime()) {} else if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {} else {
                        Attendance attendance31 = new Attendance();
                        attendance31.setInTime("");
                        attendance31.setOutTime("");
                        attendance31.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance31.setFlag(error);
                        attendance31.setMsg(noPunch);
                        attendances.add(attendance31);
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return attendances;
    }

    public static List<Attendance> getAllendance(List<DayStatus> dayStatusList, Hourt hourt, ShiftRepository shiftRepository, List<HolidayMaster> holidayMasters) throws ParseException {
        List<Attendance> attendances = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat compSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

        List<String> holidays = new ArrayList<>();
        for(HolidayMaster holidayMaster : holidayMasters) {
            holidays.add(simpleDateFormat.format(Date.from(holidayMaster.getHolidayDate().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }

        Date dt1 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        c.add(Calendar.DATE, 1);
        Date dt2 = c.getTime();
        Date datecurrent = simpleDateFormat.parse(simpleDateFormat.format(dt1));
        Date date = simpleDateFormat.parse(simpleDateFormat.format(dt2));

        int adjust = 0;
        if (dayStatusList != null && hourt != null) {
            Map<String, Shift> shiftMap = new HashMap<>();
            for (DayStatus dayStatus : dayStatusList) {
                Date dt = Date.from(dayStatus.getId().getDayno().toInstant());
                String shiftCode = "";
                if (dt.getDate() == 1) {
                    shiftCode = hourt.getSf1();
                } else if (dt.getDate() == 2) {
                    shiftCode = hourt.getSf2();
                } else if (dt.getDate() == 3) {
                    shiftCode = hourt.getSf3();
                } else if (dt.getDate() == 4) {
                    shiftCode = hourt.getSf4();
                } else if (dt.getDate() == 5) {
                    shiftCode = hourt.getSf5();
                } else if (dt.getDate() == 6) {
                    shiftCode = hourt.getSf6();
                } else if (dt.getDate() == 7) {
                    shiftCode = hourt.getSf7();
                } else if (dt.getDate() == 8) {
                    shiftCode = hourt.getSf8();
                } else if (dt.getDate() == 9) {
                    shiftCode = hourt.getSf9();
                } else if (dt.getDate() == 10) {
                    shiftCode = hourt.getSf10();
                } else if (dt.getDate() == 11) {
                    shiftCode = hourt.getSf11();
                } else if (dt.getDate() == 12) {
                    shiftCode = hourt.getSf12();
                } else if (dt.getDate() == 13) {
                    shiftCode = hourt.getSf13();
                } else if (dt.getDate() == 14) {
                    shiftCode = hourt.getSf14();
                } else if (dt.getDate() == 15) {
                    shiftCode = hourt.getSf15();
                } else if (dt.getDate() == 16) {
                    shiftCode = hourt.getSf16();
                } else if (dt.getDate() == 17) {
                    shiftCode = hourt.getSf17();
                } else if (dt.getDate() == 18) {
                    shiftCode = hourt.getSf18();
                } else if (dt.getDate() == 19) {
                    shiftCode = hourt.getSf19();
                } else if (dt.getDate() == 20) {
                    shiftCode = hourt.getSf20();
                } else if (dt.getDate() == 21) {
                    shiftCode = hourt.getSf21();
                } else if (dt.getDate() == 22) {
                    shiftCode = hourt.getSf22();
                } else if (dt.getDate() == 23) {
                    shiftCode = hourt.getSf23();
                } else if (dt.getDate() == 24) {
                    shiftCode = hourt.getSf24();
                } else if (dt.getDate() == 25) {
                    shiftCode = hourt.getSf25();
                } else if (dt.getDate() == 26) {
                    shiftCode = hourt.getSf26();
                } else if (dt.getDate() == 27) {
                    shiftCode = hourt.getSf27();
                } else if (dt.getDate() == 28) {
                    shiftCode = hourt.getSf28();
                } else if (dt.getDate() == 29) {
                    shiftCode = hourt.getSf29();
                } else if (dt.getDate() == 30) {
                    shiftCode = hourt.getSf30();
                } else if (dt.getDate() == 31) {
                    shiftCode = hourt.getSf31();
                }
                if (shiftCode != null && shiftCode.trim().length() > 0) {
                    Shift shift = null;
                    if (shiftMap.containsKey(shiftCode)) {
                        shift = shiftMap.get(shiftCode);
                    } else {
                        shift = shiftRepository.findBySftUcode(shiftCode);
                        shiftMap.put(shiftCode, shift);
                    }
                    int graceMinute = shift.getGrace();
                    int halfAfterHour = shift.getHfAfterHrs().intValue();
                    String shiftStartTime = shift.getIntime();
                    String shiftEndTime = shift.getOuttime();
                    String twoHourStartTime = shift.getIntime();
                    String twoHourEndTime = shift.getOuttime();
                    SimpleDateFormat minuteDateFormat = new SimpleDateFormat("HH:mm");
                    Date startMinute = minuteDateFormat.parse(shiftStartTime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startMinute);
                    cal.add(Calendar.MINUTE, graceMinute);
                    shiftStartTime = minuteDateFormat.format(cal.getTime());

                    Date endMinute = minuteDateFormat.parse(shiftEndTime);
                    Calendar calend = Calendar.getInstance();
                    calend.setTime(endMinute);
                    calend.add(Calendar.MINUTE, -graceMinute);
                    shiftEndTime = minuteDateFormat.format(calend.getTime());

                    Date twoHourStartMinute = minuteDateFormat.parse(twoHourStartTime);
                    Calendar calStart = Calendar.getInstance();
                    calStart.setTime(twoHourStartMinute);
                    calStart.add(Calendar.MINUTE, halfAfterHour);
                    twoHourStartTime = minuteDateFormat.format(calStart.getTime());

                    Date twoHourEndMinute = minuteDateFormat.parse(twoHourEndTime);
                    Calendar calEnd = Calendar.getInstance();
                    calEnd.setTime(twoHourEndMinute);
                    calEnd.add(Calendar.MINUTE, -halfAfterHour);
                    twoHourEndTime = minuteDateFormat.format(calEnd.getTime());

                    Date dt_chk = simpleDateFormat.parse(simpleDateFormat.format(dt));
                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(dt);
                    if (date.before(dt)) {
                    } else if (date.getTime() == dt.getTime()) {
                    } else if (holidays.contains(simpleDateFormat.format(dt)) && (dayStatus.getInTm() != null && dayStatus.getInTm().trim().length() == 0 && dayStatus.getOutTm() != null && dayStatus.getOutTm().trim().length() == 0)) {
                    } else if (dayStatus.getStatus().startsWith("WO") && (dayStatus.getInTm() != null && dayStatus.getInTm().trim().length() == 0 && dayStatus.getOutTm() != null && dayStatus.getOutTm().trim().length() == 0)) {
                        Attendance attendance = new Attendance();
                        attendance.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance.setFlag(wo);
                        attendance.setMsg(wOff);
                        attendance.setInTime("");
                        attendance.setOutTime("");
                        attendances.add(attendance);
                    } else {
                        Attendance attendance = new Attendance();
                        attendance.setAttendanceDate(dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        attendance.setInTime(dayStatus.getInTm());
                        attendance.setOutTime(dayStatus.getOutTm());
                        if (datecurrent.equals(dt_chk)) {
                        } else {
                            if ((dayStatus.getInTm() != null && dayStatus.getInTm().trim().length() > 0) && (dayStatus.getOutTm() != null && dayStatus.getOutTm().trim().length() > 0) && dayStatus.getStatus().trim().equalsIgnoreCase("P")) {
                                //Logic
                                Date inTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + dayStatus.getInTm());
                                Date outTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + dayStatus.getOutTm());

                                Date validInTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + shiftStartTime);
                                Date validOutTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + shiftEndTime);

                                Date TwoInTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + twoHourStartTime);
                                Date TwoOutTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + twoHourEndTime);
                                if ((inTime.after(TwoInTime) || outTime.before(TwoOutTime)) && !outTime.before(inTime) && (dayStatus.getStatus() != null && !dayStatus.getStatus().trim().equalsIgnoreCase("H1") && !dayStatus.getStatus().trim().equalsIgnoreCase("H2"))) {
                                    attendance.setFlag(error);
                                    attendance.setMsg(noPunch);
                                } else if (adjust < 3 && (inTime.after(validInTime) && outTime.before(inTime))) {
                                    attendance.setFlag(sl);
                                    attendance.setMsg(shortLeave);
                                    ++adjust;
                                } else if (inTime.after(validInTime) && outTime.before(inTime)) {
                                    attendance.setFlag(lt);
                                    attendance.setMsg(lateIn);
                                    ++adjust;
                                } else if (outTime.before(inTime)) {
                                } else if (adjust < 3 && (inTime.after(validInTime) || outTime.before(validOutTime))) {
                                    attendance.setFlag(inTime.after(TwoInTime) ? sl : outTime.before(TwoOutTime) ? error : sl);
                                    attendance.setMsg(inTime.after(TwoInTime) ? shortLeave : outTime.before(TwoOutTime) ? earlyOut : shortLeave);
                                    ++adjust;
                                } else {
                                    attendance.setFlag(inTime.after(validInTime) ? lt : outTime.before(validOutTime) ? lt : null);
                                    attendance.setMsg(inTime.after(validInTime) ? lateIn : outTime.before(validOutTime) ? earlyOut : null);
                                }
                            } else if (((dayStatus.getInTm() != null && dayStatus.getInTm().trim().length() > 0) || (dayStatus.getOutTm() != null && dayStatus.getOutTm().trim().length() > 0)) && (dayStatus.getStatus() != null && dayStatus.getStatus().trim().equalsIgnoreCase("A"))) {
                                attendance.setFlag(sp);
                                attendance.setMsg(singlePunch);
                            } else if (dayStatus.getStatus().trim().equalsIgnoreCase("A")) {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                                attendance.setFlag(error);
                                attendance.setMsg(noPunch);
                            } else if ((dayStatus.getInTm() != null && dayStatus.getInTm().trim().length() > 0) && (dayStatus.getOutTm() != null && dayStatus.getOutTm().trim().length() > 0)) {
                                Date inTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + dayStatus.getInTm());
                                Date outTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + dayStatus.getOutTm());
                                Date validInTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + shiftStartTime);
                                Date validOutTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + shiftEndTime);
                                Date TwoInTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + twoHourStartTime);
                                Date TwoOutTime = compSimpleDateFormat.parse(simpleDateFormat.format(dt) + " " + twoHourEndTime);
                                if (adjust == 3 && (inTime.after(validInTime) || outTime.before(validOutTime)) && (dayStatus.getStatus().trim().equalsIgnoreCase("H1") || dayStatus.getStatus().trim().equalsIgnoreCase("H2"))) {
                                    attendance.setInTime(dayStatus.getInTm());
                                    attendance.setOutTime(dayStatus.getOutTm());
                                    attendance.setFlag(dayStatus.getStatus().trim());
                                    attendance.setMsg(noPunch);
                                    adjust = 0;
                                } else if (adjust == 3 && (inTime.after(validInTime) || outTime.before(validOutTime))) {
                                    attendance.setInTime(dayStatus.getInTm());
                                    attendance.setOutTime(dayStatus.getOutTm());
                                    attendance.setFlag(leave);
                                    attendance.setMsg(dayStatus.getStatus().toUpperCase() + " Applied");
                                    adjust = 0;
                                } else if (dayStatus.getStatus().trim().equalsIgnoreCase("H1") || dayStatus.getStatus().trim().equalsIgnoreCase("H2")) {
                                    attendance.setInTime(dayStatus.getInTm());
                                    attendance.setOutTime(dayStatus.getOutTm());
                                    attendance.setFlag(dayStatus.getStatus().trim());
                                    attendance.setMsg(noPunch);
                                } else if (!dayStatus.getStatus().toUpperCase().trim().startsWith("WO*")) {
                                    attendance.setInTime(dayStatus.getInTm());
                                    attendance.setOutTime(dayStatus.getOutTm());
                                    attendance.setFlag(leave);
                                    attendance.setMsg(dayStatus.getStatus().toUpperCase() + " Applied");
                                }
                            } else if (dayStatus.getStatus().trim().equalsIgnoreCase("H1") || dayStatus.getStatus().trim().equalsIgnoreCase("H2")) {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                                attendance.setFlag(dayStatus.getStatus().trim());
                                attendance.setMsg(noPunch);
                            } else if (dayStatus.getStatus().toUpperCase().trim().startsWith("MS")) {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                                attendance.setFlag(sp);
                                attendance.setMsg(singlePunch);
                            } else if (dayStatus.getStatus().toUpperCase().trim().startsWith("OD")) {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                                attendance.setFlag(overtime);
                                attendance.setMsg(dayStatus.getStatus().toUpperCase() + " Applied");
                            } else if (dayStatus.getStatus().toUpperCase().trim().startsWith("WO*")) {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                            } else {
                                attendance.setInTime(dayStatus.getInTm());
                                attendance.setOutTime(dayStatus.getOutTm());
                                attendance.setFlag(leave);
                                attendance.setMsg(dayStatus.getStatus().toUpperCase() + " Applied");
                            }
                        }
                        attendances.add(attendance);
                    }
                }
            }
        }
        return attendances;
    }
}
