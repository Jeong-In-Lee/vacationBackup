import axios from 'axios';
import { API_SERVER_HOST } from './todoApi';

const prefix = `${API_SERVER_HOST}/api/products`;

export const postAdd = async (product) => {
    const header = { Headers: { 'Content-Type': 'multipart/form-data' } };
    const res = await axios.post(`${prefix}/`, product, header);
    return res.data;
};

export const getList = async (pageParam) => {
    const { page, size } = pageParam;

    const res = await axios.get(`${prefix}/list`, { params: { page, size } });

    return res.data;
};
