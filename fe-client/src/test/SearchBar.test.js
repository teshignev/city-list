import { render, screen } from '@testing-library/react';
import SearchBar from '../components/SearchBar';

test('renders component', () => {
  const { container } = render(<SearchBar/>);

  const elements = container.getElementsByClassName('SearchBar');

  expect(elements.length).toBe(1);
});