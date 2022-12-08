package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)) throw new IllegalArgumentException("Invalid date");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isValidDate(int day, int month, int year) {
        if (year < 0) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if (month == 2 && (day > 29 || (!isLeapYear(year) && day > 28))) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate() {
        return new Date((isLastDayOfMonth() ? 1 : this.day+1),
                (isLastDayOfMonth() ? ((this.month == 12) ? 1 : this.month + 1) : this.month),
                (this.day==31 && this.month==12) ? this.year+1 : this.year);
    }

    private boolean isLastDayOfMonth() {
        return (day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12))
                || (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11))
                || (day == 29 && month == 2 && isLeapYear(year))
                || (day == 28 && month == 2 && !isLeapYear(year));
    }

    public Date previousDate() {
        return new Date(getPreviousDay(),
                (day == 1 ? ((this.month == 1) ? 12 : this.month - 1) : this.month),
                (this.day==1 && this.month==1) ? this.year-1 : this.year);
    }

    private int getPreviousDay(){
        if (this.day == 1) {
            if (this.month == 3) {
                return isLeapYear(this.year) ? 29 : 28;
            } else if (this.month == 5 || this.month == 7 || this.month == 10 || this.month == 12) {
                return 30;
            } else {
                return 31;
            }
        }
        return this.day - 1;
    }

    public int compareTo(Date other) {
        if(other == null) throw new NullPointerException("Date object is null");
        if(other.equals(this)) return 0;
        if(this.year < other.year) return -1;
        if(this.year > other.year) return 1;
        if(this.month < other.month) return -1;
        if(this.month > other.month) return 1;
        if(this.day <= other.day) return -1;
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Date)) return false;
        Date other = (Date) obj;
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear(){
        return this.year;
    }
}