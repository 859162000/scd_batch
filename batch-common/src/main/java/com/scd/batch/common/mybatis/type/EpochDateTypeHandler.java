package com.scd.batch.common.mybatis.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeReference;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@MappedTypes(Date.class)
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class EpochDateTypeHandler extends TypeReference<Date> implements TypeHandler<Date> {

    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(getDefaultTimestamp(parameter)));
    }

    public Date getResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnName);
        return rs.wasNull() ? null : getDefaultDate(sqlTimestamp);
    }

    public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp sqlTimestamp = rs.getTimestamp(columnIndex);
        return rs.wasNull() ? null : getDefaultDate(sqlTimestamp);
    }

    public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
        return cs.wasNull() ? null : getDefaultDate(sqlTimestamp);
    }

    /**
     *
     * @param parameter
     * @return
     */
    protected long getDefaultTimestamp(Date parameter) {
        return parameter.getTime();
    }

    /**
     *
     * @param sqlTimestamp
     * @return
     */
    protected Date getDefaultDate(Timestamp sqlTimestamp) {
        if (sqlTimestamp == null) {
            return null;
        }

        return new Date(sqlTimestamp.getTime());
    }

}
