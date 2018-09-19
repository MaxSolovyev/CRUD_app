package com.crud.dao.executor;

import com.crud.util.DBException;

import java.sql.*;

public class Executor {
	private final Connection connection;

	public Executor(Connection connection) {
		this.connection = connection;
	}

	public void execUpdate(String update) throws DBException {
		try {
			connection.setAutoCommit(false);

			Statement stmt = connection.createStatement();
			stmt.execute(update);
			stmt.close();

			connection.commit();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				connection.rollback();
			} catch (SQLException exRlb) {
				throw new DBException(exRlb);
			}
			throw new DBException(ex);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException exFin) {
				throw new DBException(exFin);
			}
		}
	}

	public long execUpdateWithKeys(String update) throws DBException {
		long id = 0;
		try {
			connection.setAutoCommit(false);
			try (PreparedStatement ps = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS)) {
				ps.executeUpdate();
				try (ResultSet rs = ps.getGeneratedKeys()) {
					while(rs.next()) {
						id=rs.getInt(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection.commit();
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException exRlb) {
				throw new DBException(exRlb);
			}
			throw new DBException(ex);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException exFin) {
				throw new DBException(exFin);
			}
		}
		return id;
	}

	public <T> T execQuery(String query, ExecutorHelper<T> helper) throws DBException {
		T result = null;
		try {
			connection.setAutoCommit(false);

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			result = helper.help(resultSet);
			resultSet.close();
			statement.close();

			connection.commit();
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException exRlb) {
				throw new DBException(exRlb);
			}
			throw new DBException(ex);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException exFin) {
				throw new DBException(exFin);
			}
		}
		return result;
	}
}